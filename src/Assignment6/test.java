package Assignment6;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class test {
	 // verts normals and faces of the polygon
	
	static String verts="", tex ="", normals="";
	static String vertexIndices="" , normalsIndices ="";
    
    static ArrayList<Integer>  vertIndices = new ArrayList();
    static ArrayList<Integer>  texIndices = new ArrayList();
    static ArrayList<Integer>  normalIndices = new ArrayList();
	
public static void main(String[] args){
    // The name of the file to open.
    String fileName = "test.txt";

    // This will reference one line at a time
    String line = null;


    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String values[];
        while((line = bufferedReader.readLine()) != null) {
        	values = line.trim().split(" ");
            switch (values[0]) {
            case "vt":
                tex = tex + processFloatVertices(values);
                break;
            case "vn":
            	String myline;
            	if((myline = processFloatNormals(values)) != null)
                normals= normals + myline;
                break;
            case "v":
                verts= verts + processFloatVertices(values);
                break;
            case "f":
                processIndicies(values);
            default:
                break;
        }
        }   

        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '" 
            + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }
    
    
    PrintWriter writer;
	try {
		
		writer = new PrintWriter("normals.txt");
		writer.println(normals);
	    writer.close();
	    
		writer = new PrintWriter("vertices.txt");
		writer.println(verts);
	    writer.close();
	    
		writer = new PrintWriter("elements.txt");
		writer.println(vertexIndices);
	    writer.close();
	    
		writer = new PrintWriter("normalIndices.txt");
		writer.println(normalsIndices);
	    writer.close();
	    
	    
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

static String processFloatNormals(String[] values){
    StringBuffer str = new StringBuffer();
    String temp1 = "";
    for (int i = 1; i < values.length; ++i){
    	if(values[i] != null)
        temp1 = values[i]+"00f, ";
    	str.append(temp1);
    }
    
    return str.toString();
}

static String processFloatVertices(String[] values){
    StringBuffer str = new StringBuffer();
    String temp1 = "";
    for (int i = 1; i < values.length; ++i){
    	if(values[i] != null)
        temp1 = values[i]+"f, ";
    	str.append(temp1);
    }
    
    return str.toString();
}

/**
 * process the face indicies. Used to create faces from the list of vertices.
 * Used to create the buffers
 * @param values - one line from the obj file to process
 */
static void processIndicies(String[] values){
    String val[];
    for (int i = 1; i < values.length; ++i){
        val = values[i].split("//");
        if(val.length != 2){
            System.err.println("Length of face indices out of bounds.");
            System.err.println(Arrays.toString(val));
            System.exit(1);
        }
        vertexIndices = vertexIndices + (Integer.parseInt(val[0]) - 1) +", ";
        normalsIndices = normalsIndices +  (Integer.parseInt(val[1]) - 1) + ", ";
        
    }
}

}
