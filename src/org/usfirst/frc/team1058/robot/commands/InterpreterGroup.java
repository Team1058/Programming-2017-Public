package org.usfirst.frc.team1058.robot.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.usfirst.frc.team1058.robot.ParseHelper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class InterpreterGroup extends CommandGroup {

    public InterpreterGroup(){
    	
    }
    
    
    public boolean init(){
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	String filePath = "/tmp/test.txt";
    	
    	ArrayList<Object> arguments = new ArrayList<Object>();
		
		FileReader fileReader = null;
		try {
			File file = new File(filePath);
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String scriptLine = null;
		
		try {
			
			while((scriptLine = bufferedReader.readLine()) != null){
				//s,DriveToPostion,100,100,12
				
				
				String[] tokens = scriptLine.split(",");
								
				
				
				//DriveToPosition(double left, double right, double maxvoltage)
				Class<?> c = null;
				try {
					c = Class.forName("org.usfirst.frc.team1058.robot.commands."+tokens[1]);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return false;
				}
				
				//constr = c.getConstructor(double.class, double.class, double.class);
				//c.getConstructor().newInstance(arguments);
				
				
				
				try {
					Constructor<?>[] constructor = c.getConstructors();
					Class<?>[] constructParameters = constructor[0].getParameterTypes();
					for (int x = 0; x < constructParameters.length; x++){
						if (constructParameters[x].equals(Integer.TYPE) || constructParameters[x].equals(Integer.class)){
							if (ParseHelper.isNumber(tokens[x])){
								arguments.add(Double.parseDouble(tokens[x]));
							}
						}else if(constructParameters[x].equals(Double.TYPE) || constructParameters[x].equals(Double.class)){
							if (ParseHelper.isNumber(tokens[x])){
								arguments.add(Double.parseDouble(tokens[x]));
							}
						}else if (constructParameters[x].equals(Boolean.TYPE) || constructParameters[x].equals(Boolean.class)){
							if (ParseHelper.isBoolean(tokens[x])){
								arguments.add(Boolean.parseBoolean(tokens[x]));
							}
						}else if (constructParameters[x].equals(String.class)){
							if (ParseHelper.isString(tokens[x])){
								arguments.add(tokens[x]);
							}
						}
					}
					if (tokens[0].equalsIgnoreCase("s")){
						try {
							addSequential((Command) c.getConstructor(constructParameters).newInstance(arguments));
						} catch (InstantiationException | IllegalAccessException | InvocationTargetException
								| NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return false;
						}
					}else if(tokens[0].equalsIgnoreCase("p")){
						try {
							addParallel((Command) c.getConstructor(constructParameters).newInstance(arguments));
						} catch (InstantiationException | IllegalAccessException | InvocationTargetException
								| NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return false;
						}
					}
				}catch (IllegalArgumentException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
				}
				
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
    }
}
