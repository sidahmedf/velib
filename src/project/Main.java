package project;
import project.controlCenter.ControlCenter;
import project.decorator.bikeDecorator.BasketDecorator;
import project.decorator.electricalScooterdecorator.GPSDecorator;
import project.role.Client;
import project.role.Human;
import project.role.Repairer;
import project.role.Thief;
import project.station.Station;
import project.station.Exception.StationEmptyException;
import project.station.Exception.StationFullException;
import project.strategy.RandomStrategy;
import project.vehicule.Vehicule;
import project.vehicule.bike.ClassicBike;
import project.vehicule.bike.ElectricalBike;
import project.vehicule.electricalScooter.ElectricalScooter;

public class Main {

    // Tracking empty and full intervals for each station
    private static int[] fullIntervals = new int[3]; // 3 stations
    private static int[] emptyIntervals = new int[3]; // 3 stations

    public static void main(String[] args) {
        try {
            // Environment Initialization Step
            System.out.println("Environment Initialization started...");
            Thread.sleep(1000); // Adding delay for visual effect

            // Access the singleton instance of ControlCenter
            ControlCenter controlCenter = ControlCenter.getInstance();

            // Create 3 stations with capacity 5
            Station station1 = new Station(1, 5);
            Station station2 = new Station(2, 5);
            Station station3 = new Station(3, 5);

            // Add stations to control center
            controlCenter.addStation(station1);
            controlCenter.addStation(station2);
            controlCenter.addStation(station3);

            // Create 10 vehicles (5 classic bikes, 3 electrical bikes, 2 scooters)
            Vehicule[] vehicles = new Vehicule[10];

            // Directly applying decorators
            vehicles[0] = new BasketDecorator(new ClassicBike(1, 10)); // First bike with basket
            vehicles[1] = new ClassicBike(2, 12);
            vehicles[2] = new BasketDecorator(new ClassicBike(3, 11)); // Third bike with basket
            vehicles[3] = new ClassicBike(4, 15);
            vehicles[4] = new ClassicBike(5, 13);

            // Electrical bikes (no decoration applied)
            vehicles[5] = new ElectricalBike(6, 8);
            vehicles[6] = new ElectricalBike(7, 9);
            vehicles[7] = new ElectricalBike(8, 7);

            // Create and decorate ElectricalScooters with GPS
            vehicles[8] = new GPSDecorator(new ElectricalScooter(9, 5, 25)); // First scooter with GPS
            vehicles[9] = new GPSDecorator(new ElectricalScooter(10, 6, 30)); // Second scooter with GPS

            // Distribute vehicles across stations
            for (int i = 0; i < vehicles.length; i++) {
                try {
                    // Attempt to add each vehicle to a station
                    station1.takeVehicule(vehicles[i]);
                } catch (StationFullException e) {
                    try {
                        station2.takeVehicule(vehicles[i]);
                    } catch (StationFullException e2) {
                        try {
                            station3.takeVehicule(vehicles[i]);
                        } catch (StationFullException e3) {
                            // No print statement here as per your request
                        }
                    }
                }
            }

            // Print the environment initialization summary with the desired format
            System.out.println("Total of 3 stations and " + vehicles.length + " vehicles initialized.");
            System.out.println("Environment Initialization ended.");
            System.out.println("\n");

            Thread.sleep(1000); // Adding delay before moving to the next step

            // ---------------------------

            // Human Initialization Step
            System.out.println("Human Initialization started...");
            Thread.sleep(1000); // Adding delay for visual effect

            // Create humans (3 clients, 2 repairers, 1 thief) with only first name
            Client client1 = new Client(1, "Élise");
            Client client2 = new Client(2, "Pierre");
            Client client3 = new Client(3, "Sophie");
            Repairer repairer1 = new Repairer(4, "Julien");
            Repairer repairer2 = new Repairer(5, "Claire");
            Thief thief = new Thief(6, "Antoine");

            // Array of humans to interact with stations
            Human[] humans = {client1, client2, client3, repairer1, repairer2, thief};

            // Print the human initialization summary with the desired format
            int clientsCount = 3;
            int repairersCount = 2;
            int thievesCount = 1;
            int totalHumans = humans.length;

            System.out.println("Total of " + totalHumans + " humans added: " +
                               clientsCount + " clients, " +
                               repairersCount + " repairers, " +
                               thievesCount + " thief.");
            System.out.println("Human Initialization ended.");
            System.out.println("\n");

            Thread.sleep(1000); // Adding delay before moving to the next step

            // ---------------------------

            // Print the current status before simulation
            printCurrentStationStatus(controlCenter);

            // Simulation Step
            System.out.println("Simulation started...");
            Thread.sleep(1000); // Adding delay for visual effect

            // Interaction loop: Let each human interact with each station a few times
            for (int i = 0; i < 5; i++) { // Repeat 5 times for interaction
                for (Human human : humans) {
                    // Randomly pick one of the three stations for the human to interact with
                    Station randomStation = (int) (Math.random() * 3) == 0 ? station1 : (int) (Math.random() * 3) == 1 ? station2 : station3;

                    // Print the beginning of the interaction
                    System.out.println("------------------------------------------------");
                    
                    // Print the descriptive output for the interaction
                    System.out.println(human.getName() + " (" + human.getClass().getSimpleName() + ") is interacting with Station " 
                                       + randomStation.getId() + ".");

                    // Human interacts with the chosen station
                    human.interact(randomStation);
                    
                    // Update full/empty interval counts after each interaction
                    updateIntervalCounts(randomStation);

                    // Check and trigger redistribution if needed
                    checkAndRedistribute();

                    Thread.sleep(500); // Sleep after each human interaction to observe the effect
                }
            }

            // Print the current status after simulation
            printCurrentStationStatus(controlCenter);

            // Print the simulation summary
            System.out.println("------------------------------------------------");
            System.out.println("Simulation ended.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Private method to update the empty/full interval counts
    private static void updateIntervalCounts(Station station) {
        if (station.isEmpty()) {
            emptyIntervals[station.getId() - 1]++; // Increment empty count for the station
            fullIntervals[station.getId() - 1] = 0; // Reset full count
        } else if (station.isFull()) {
            fullIntervals[station.getId() - 1]++; // Increment full count for the station
            emptyIntervals[station.getId() - 1] = 0; // Reset empty count
        } else {
            emptyIntervals[station.getId() - 1] = 0;
            fullIntervals[station.getId() - 1] = 0;
        }
    }

    // Private method to check if redistribution is needed and trigger it
    private static void checkAndRedistribute() {
        for (int i = 0; i < fullIntervals.length; i++) {
            // If station is full for more than 2 intervals
            if (fullIntervals[i] > 2) {
                System.out.println("**************************************************************");
                System.out.println("!!! ALERT: Station " + (i + 1) + " IS FULL for more than 2 intervals !!!");
                System.out.println("!!! BEGINNING RE-DISTRIBUTION PROCESS !!!");
                System.out.println("**************************************************************");
                try {
                    ControlCenter.getInstance().reallocate(); // Trigger redistribution
                    System.out.println("!!! REDISTRIBUTION ENDED !!!");
                } catch (StationFullException | StationEmptyException e) {
                    e.printStackTrace();
                }
                fullIntervals[i] = 0; // Reset after redistribution
            }
    
            // If station is empty for more than 2 intervals
            if (emptyIntervals[i] > 2) {
                System.out.println("**************************************************************");
                System.out.println("!!! ALERT: Station " + (i + 1) + " IS EMPTY for more than 2 intervals !!!");
                System.out.println("!!! BEGINNING RE-DISTRIBUTION PROCESS !!!");
                System.out.println("**************************************************************");
                try {
                    ControlCenter.getInstance().reallocate(); // Trigger redistribution
                    System.out.println("!!! REDISTRIBUTION ENDED !!!");
                } catch (StationFullException | StationEmptyException e) {
                    e.printStackTrace();
                }
                emptyIntervals[i] = 0; // Reset after redistribution
            }
        }
    }

    // Private method to print the current station status
    private static void printCurrentStationStatus(ControlCenter controlCenter) {
        System.out.println("------------------------------------------------");
        System.out.println("Current Vehicle Distribution:");

        // Iterate through stations and print the status
        for (Station station : controlCenter.getStations()) {
            System.out.println("Station " + station.getId() + " | Capacity: " + station.getCapacity() +
                               " | Vehicles: " + station.getVehicles().size());
        }

        // Print total vehicles and stations
        int totalVehicles = controlCenter.getStations().stream()
                                        .mapToInt(station -> station.getVehicles().size())
                                        .sum();
        System.out.println("Total of " + totalVehicles + " vehicles distributed around " +
                           controlCenter.getStations().size() + " stations.");
    }
}