import javax.swing.JOptionPane;

abstract class WaterTank {
	
	protected int tankCapacity;
    protected int currentWaterLevel;  

    public WaterTank(int tankCapacity) {
        this.tankCapacity = tankCapacity;
        this.currentWaterLevel = 0;
    }
    
    public abstract void fillTank(int liters);
    public abstract void useWater(int liters);
    public abstract String checkStatus();
    
    public int showTankCapacity() {
        return tankCapacity;
    }
    
    public int showCurrentLevel() {
        return currentWaterLevel;
    }
    
    boolean isFull() {
        return currentWaterLevel == tankCapacity;
    }
    
    boolean isEmpty() {
        return currentWaterLevel == 0;
    }
    
}

class HomeTank extends WaterTank {
    public HomeTank() {
        super(200);
    }
    
    @Override
    public void fillTank(int liters) {
        if (liters <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (currentWaterLevel + liters > tankCapacity) {
            int maxFillableAmount = tankCapacity - currentWaterLevel;
            currentWaterLevel = tankCapacity;
            JOptionPane.showMessageDialog(null, 
                "Can only add " + maxFillableAmount + " liters. Tank is full.\n" +
                "Current level: " + currentWaterLevel + "/" + tankCapacity, "", JOptionPane.PLAIN_MESSAGE);
        } else {
        	currentWaterLevel += liters;
            JOptionPane.showMessageDialog(null, 
                liters + " liters added. Current level: " + currentWaterLevel + "/" + tankCapacity, "", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public void useWater(int liters) {
        if (liters <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a positive number.", "", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        
        if (liters > currentWaterLevel) {
            int availableWater = currentWaterLevel;
            currentWaterLevel = 0;
            JOptionPane.showMessageDialog(null, 
                "Only " + availableWater + " liters available. Tank is now empty.\n" +
                "Current level: " + currentWaterLevel + "/" + tankCapacity, "", JOptionPane.PLAIN_MESSAGE);
        } else {
        	currentWaterLevel -= liters;
            JOptionPane.showMessageDialog(null, 
                liters + " liters used. Current level: " + currentWaterLevel + "/" + tankCapacity, "", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public String checkStatus() {
        if (isFull()) {
            return "Tank is now full.";
        } else if (isEmpty()) {
            return "Tank is now empty.";
        } else {
            return "Tank is currently in use.";
        }
    }
    
}

class BuildingTank extends WaterTank {
    public BuildingTank() {
        super(2000);
    }
    
    @Override
    public void fillTank(int liters) {
        if (liters <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (currentWaterLevel + liters > tankCapacity) {
            int maxFillable = tankCapacity - currentWaterLevel;
            currentWaterLevel = tankCapacity;
            JOptionPane.showMessageDialog(null, 
                "Can only add " + maxFillable + " liters. Tank is now full.\n" +
                "Current level: " + currentWaterLevel + "/" + tankCapacity, "", JOptionPane.PLAIN_MESSAGE);
        } else {
        	currentWaterLevel += liters;
            JOptionPane.showMessageDialog(null, 
                liters + " liters added. Current level: " + currentWaterLevel + "/" + tankCapacity, "", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public void useWater(int liters) {
        if (liters <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (liters > currentWaterLevel) {
            int availableWater = currentWaterLevel;
            currentWaterLevel = 0;
            JOptionPane.showMessageDialog(null, 
                "Only " + availableWater + " liters available. Tank is now empty.\n" +
                "Current level: " + currentWaterLevel + "/" + tankCapacity);
        } else {
        	currentWaterLevel -= liters;
            JOptionPane.showMessageDialog(null, 
                liters + " liters used. Current level: " + currentWaterLevel + "/" + tankCapacity, "", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public String checkStatus() {
        if (isFull()) {
            return "Tank is now full.";
        } else if (isEmpty()) {
            return "Tank is now empty.";
        } else {
            return "Tank is currently in use.";
        }
    }
}

public class WaterTankMain {
    public static void main(String[] args) {
        WaterTank tank = null;
        
        String[] options = {"Home Tank", "Building Tank"};
        int choice = JOptionPane.showOptionDialog(null, 
            "Enter type of tank:", "", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, options, options[0]);
        
        if (choice == 0) {
            tank = new HomeTank();
            JOptionPane.showMessageDialog(null, "Home Tank selected (200 liters capacity)", "", JOptionPane.PLAIN_MESSAGE);
        } else if (choice == 1) {
            tank = new BuildingTank();
            JOptionPane.showMessageDialog(null, "Building Tank selected (2000 liters capacity)", "", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tank selected. Program ended.", "", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        
        do {
            
            JOptionPane.showMessageDialog(null, 
                "Current Status: " + tank.checkStatus() + "\n" +
                "Water level: " + tank.showCurrentLevel() + "/" + tank.showTankCapacity(), "", JOptionPane.PLAIN_MESSAGE);
            
            String[] actions = {"Fill Tank", "Use Water", "Check Status", "Exit"};
            int action = JOptionPane.showOptionDialog(null, 
                "What would you like to do?", "", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, actions, actions[0]);
            
            switch (action) {
                case 0: 
                    if (tank.isFull()) {
                        JOptionPane.showMessageDialog(null, "Tank is already full.", "Tank Status", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    try {
                        String fillInput = JOptionPane.showInputDialog("Enter liters to fill:");
                        if (fillInput != null && !fillInput.trim().isEmpty()) {
                            int litersToFill = Integer.parseInt(fillInput);
                            tank.fillTank(litersToFill);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case 1: 
                    if (tank.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Tank is already empty.");
                        break;
                    }
                    try {
                        String useInput = JOptionPane.showInputDialog("Enter liters to use:");
                        if (useInput != null && !useInput.trim().isEmpty()) {
                            int litersToUse = Integer.parseInt(useInput);
                            tank.useWater(litersToUse);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case 2: 
                    JOptionPane.showMessageDialog(null, 
                        "Tank Status: " + tank.checkStatus() + "\n" +
                        "Water level: " + tank.showCurrentLevel() + "/" + tank.showTankCapacity(), "", JOptionPane.PLAIN_MESSAGE);
                    break;
                    
                case 3: 
                case -1: 
                    JOptionPane.showMessageDialog(null, "Program ended by user.", "", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                    break;
            }
            
            if (tank.isFull()) {
                JOptionPane.showMessageDialog(null, 
                    "Tank is now full. Program ended.\n" +
                    "Final level: " + tank.showCurrentLevel() + "/" + tank.showTankCapacity(), "", JOptionPane.PLAIN_MESSAGE);
                break;
            } else if (tank.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Tank is now empty, Program ended.\n" +
                    "Final level: " + tank.showCurrentLevel() + "/" + tank.showTankCapacity(), "", JOptionPane.PLAIN_MESSAGE);
                break;
            }
        } while (true);
        
        JOptionPane.showMessageDialog(null, "Thank you for using the Water Tank Monitoring System.", "", JOptionPane.PLAIN_MESSAGE);
        
        System.exit(0);
    }
}