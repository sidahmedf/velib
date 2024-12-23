package project.strategy;
import java.util.List;

import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;

/** interface of RedistributionStrategy */
public interface RedistributionStrategy {

    /** Method to redistribute the stations
     * @param stations the list of stations
     * @throws StationEmptyException 
     * @throws StationFullException 
     */
    public void redistribute(List<Station> stations) throws StationEmptyException, StationFullException;
}
