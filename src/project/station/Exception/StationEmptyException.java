package project.station.Exception;

/** Exception for when a station is empty */
public class StationEmptyException extends Exception{

    /** Constructor of StationEmptyException
     * @param message message of the exception
     */
    public StationEmptyException(String message) {
        super(message) ;
    }

}
