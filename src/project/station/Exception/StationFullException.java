package project.station.Exception;

/** Exception for when a station is full */
public class StationFullException extends Exception {

    /** Constructor of StationFullException
     * @param message message of the exception
     */
    public StationFullException(String message) {
        super(message) ;
    }

}
