package graph;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Assign4 {

	/** gets the input from file */
	public static DiGraph getInput(String file){
		DiGraph graph = null;
		try{
			int length = countLines(file);
			int [] [] matrix = new int[length][length];
			//System.out.println(length);
			graph = new DiGraph(length);
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			String temp;
			for (int i = 0; i < length; i++){
				temp = in.readLine();
				int col = 0;
				for (int j = 0; j < temp.length(); j++){
					char x = temp.charAt(j);
					if (x == '0' || x == '1'){
						if (x =='1') {
							matrix[i][col] = 1;
						}
						col++;
					}
				}
			}
			graph.setAdjMatrix(matrix);
			in.close();
		} catch (IOException e){
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException e){
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
		return graph;
	}
	
	public static void giveOutputDepth(DiGraph graph, String ifile, String ofile){
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(ifile));
			int length = countLines(ifile);
			LinkedList<Stack<Integer>> results = new LinkedList<Stack<Integer>>();
			String temp, temp2;
			int start, end;
			for (int i = 0; i < length; i++){
				temp = in.readLine();
				temp2 = "";
				int k;
				for (k = 0; temp.charAt(k) != 9; k++){
					temp2 += Character.toString(temp.charAt(k));
				}
				start = Integer.parseInt(temp2);
				temp2 = "";
				for (k++; k < temp.length(); k++){
					temp2 += Character.toString(temp.charAt(k));
				}
				end = Integer.parseInt(temp2);
				//System.out.println("Start: " + start + " | End: " + end);
				results.add(graph.findPathDepth(start, end));
			}
			
			Node<Stack<Integer>> n = results.head();
			String result = "";
			while (n != null){
				while (!n.getData().isEmpty()){
					result += Integer.toString(n.getData().pop().intValue());
					if (!n.getData().isEmpty()) result += ",	";
				}
				result += "\n";
				n = n.getNext();
			}
			Files.write(Paths.get(ofile), result.getBytes(), StandardOpenOption.CREATE);
			
			in.close();
		} catch (IOException e){
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}
	
	public static void giveOutputBreadth(DiGraph graph, String ifile, String ofile){
		try {
			BufferedReader in = new BufferedReader(new FileReader(ifile));
			int length = countLines(ifile);
			LinkedList<Stack<Integer>> results = new LinkedList<Stack<Integer>>();
			String temp, temp2;
			int start, end;
			for (int i = 0; i < length; i++){
				temp = in.readLine();
				temp2 = "";
				int k;
				for (k = 0; temp.charAt(k) != 9; k++){
					temp2 += Character.toString(temp.charAt(k));
				}
				start = Integer.parseInt(temp2);
				temp2 = "";
				for (k++; k < temp.length(); k++){
					temp2 += Character.toString(temp.charAt(k));
				}
				end = Integer.parseInt(temp2);
				results.add(graph.findPathBreadth(start, end));
			}
			
			Node<Stack<Integer>> n = results.head();
			String result = "";
			while (n != null){
				while (!n.getData().isEmpty()){
					result += Integer.toString(n.getData().pop().intValue());
					if (!n.getData().isEmpty()) result += ",	";
				}
				result += "\n";
				n = n.getNext();
			}
			Files.write(Paths.get(ofile), result.getBytes(), StandardOpenOption.CREATE);
			
			in.close();
		} catch (IOException e){
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Count the lines in a file, from stackoverflow.com
	 * NOTE: The input files should have a new blank line at the end of the file
	 * @param filename
	 * @return Number of lines
	 * @throws IOException
	 */
	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
	
	public static void main(String [] args){
		
		if (args.length != 4) System.exit(1);
		DiGraph test = getInput(args[0]);
		
		giveOutputDepth(test, args[1], args[2]);
		giveOutputBreadth(test, args[1], args[3]);
		
		if (test.isCyclic()) System.out.println("The graph is cyclic");
		else System.out.println("The graph is not cyclic");
	}
}
