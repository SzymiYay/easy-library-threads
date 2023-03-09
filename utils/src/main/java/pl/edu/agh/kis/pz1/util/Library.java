package pl.edu.agh.kis.pz1.util;

import java.util.concurrent.Semaphore;

/**
 * Library class - a class that simulates a library with readers and writers
 */
public class Library {
    private static final int MAX_READERS = 5;
    private final Semaphore libraryRoom;
    private final Semaphore queue;

    private int readersInLibrary = 0;
    private int writersInLibrary = 0;
    private int readersInQueue = 0;
    private int writersInQueue = 0;

    /**
     * Creates a library with a limited number of readers.
     */
    public Library() {
        libraryRoom = new Semaphore(MAX_READERS, true);
        queue = new Semaphore(1, true);
    }

    /**
     * Adds reader to the library.
     *
     * @param id id of the reader
     * @throws InterruptedException
     */
    public void addReader(int id) throws InterruptedException {

        synchronized (this) {
            readersInQueue++;
        }

        queue.acquire();
        Logger.log("[...] Reader " + id + " is waiting for a permit", ConsoleColors.YELLOW);
        libraryRoom.acquire();
        Logger.log("[ + ] Reader " + id + " enters the library", ConsoleColors.GREEN);

        synchronized (this) {
            readersInQueue--;
            readersInLibrary++;

            Logger.log(getData(), ConsoleColors.WHITE);
        }

        queue.release();
    }

    /**
     * Releases a permit, returning it to the semaphore.
     * @param id id of the reader
     */
    public void releaseReader(int id) {
        Logger.log("[ - ] Reader " + id + " leaves the library", ConsoleColors.RED);
        libraryRoom.release();

        synchronized (this) {
            readersInLibrary--;
        }
    }

    /**
     * Writers are not allowed to enter the library if there are any readers inside.
     * @param id Writer's id.
     * @throws InterruptedException
     */
    public void addWriter(int id) throws InterruptedException {

        synchronized (this) {
            writersInQueue++;
        }

        queue.acquire();
        Logger.log("[...] Writer " + id + " is waiting for a permit", ConsoleColors.YELLOW);
        libraryRoom.acquire(5);
        Logger.log("[ + ] Writer " + id + " enters the library", ConsoleColors.GREEN);

        synchronized (this) {
            writersInQueue--;
            writersInLibrary++;

            Logger.log(getData(), ConsoleColors.WHITE);
        }

        queue.release();
    }

    /**
     * Method to release a writer.
     * @param id id of the writer
     */
    public void releaseWriter(int id) {
        Logger.log("[ - ] Writer " + id + " leaves the library", ConsoleColors.RED);
        libraryRoom.release(5);

        synchronized (this) {
            writersInLibrary--;
        }
    }

    /**
     * Method to get data about readers and writers in the library.
     * @return data about readers and writers in the library
     */
    public String getData() {
        return "\n------------------------------\n|    Readers in library: " + readersInLibrary + "   | " +
                "\n|    Writers in library: " + writersInLibrary + "   | " +
                "\n|    Readers in queue: " + readersInQueue + "     | " +
                "\n|    Writers in queue: " + writersInQueue + "     | " +
                "\n------------------------------\n";
    }

    /** Returns the number of available permits.
     * @return the available
     */
    public Semaphore getSemaphoreLibrary() {
        return libraryRoom;
    }

    /**
     * Returns the number of available permits.
     * @return the available permits
     */
    public Semaphore getSemaphoreQueue() {
        return queue;
    }

    /**
     * Returns the number of readers in the library.
     * @return the number of readers in the library
     */
    public int getReadersInLibrary() {
        return readersInLibrary;
    }

    /**
     * Returns the number of writers in the library.
     * @return the number of writers in the library
     */
    public int getWritersInLibrary() {
        return writersInLibrary;
    }

    /**
     * Returns the number of readers in the queue.
     * @return the number of readers in the queue
     */
    public int getReadersInQueue() {
        return readersInQueue;
    }

    /**
     * Returns the number of writers in the queue.
     * @return the number of writers in the queue
     */
    public int getWritersInQueue() {
        return writersInQueue;
    }
}
