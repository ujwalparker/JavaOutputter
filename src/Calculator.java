import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

public class Calculator
{
	public static void main(String[] args)
	{
		try
		{
			FileUtils.deleteQuietly(new File("Logic.java"));
			String newprogram = "import java.io.BufferedReader; import java.io.DataOutputStream; import java.io.File; import java.io.IOException; import java.io.InputStreamReader; import java.io.PrintWriter; import java.io.StringWriter; import java.net.ServerSocket; import java.net.Socket; import org.apache.commons.io.FileUtils; public class Logic { public static void main(String[] args) throws IOException { try { String clientSentence; ServerSocket welcomeSocket = new ServerSocket(6789); while (true) { Socket connectionSocket = welcomeSocket.accept(); BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); clientSentence = inFromClient.readLine(); System.out.println(clientSentence); String input = clientSentence, output = \"\"; { if (null != input && input.length() > 0 && null != input) { final String calExpr = input; int lhsInt = 0, rhsInt = 0; String lhs = null, rhs = null; if (calExpr.contains(\"++\") && calExpr.indexOf(\"++\") > 0) { lhs = calExpr.substring(0, calExpr.indexOf(\"++\")); try { lhsInt = Integer.valueOf(lhs); } catch (Exception e) { output = \"Please enter valid LEFT HAND SIDE. You entered :'\" + calExpr + \"'\"; } output = calExpr + \"=\" + ++lhsInt; } else if (calExpr.contains(\"--\") && calExpr.indexOf(\"--\") > 0) { lhs = calExpr.substring(0, calExpr.indexOf(\"--\")); try { lhsInt = Integer.valueOf(lhs); } catch (Exception e) { output = \"Please enter valid LEFT HAND SIDE. You entered :'\" + calExpr + \"'\"; } output = calExpr + \"=\" + --lhsInt; } else if (calExpr.contains(\"+\") && calExpr.indexOf(\"+\") > 0) { lhs = calExpr.substring(0, calExpr.indexOf(\"+\")); try { lhsInt = Integer.valueOf(lhs); } catch (Exception e) { output = \"Please enter valid LEFT HAND SIDE. You entered :'\" + calExpr + \"'\"; } rhs = calExpr.substring(calExpr.indexOf(\"+\") + 1, calExpr.length()); try { rhsInt = Integer.valueOf(rhs); } catch (Exception e) { output = \"Please enter valid RIGHT HAND SIDE. You entered :'\" + calExpr + \"'\"; } output = calExpr + \"=\" + (lhsInt + rhsInt); } else if (calExpr.contains(\"*\") && calExpr.indexOf(\"*\") > 0) { lhs = calExpr.substring(0, calExpr.indexOf(\"*\")); try { lhsInt = Integer.valueOf(lhs); } catch (Exception e) { output = \"Please enter valid LEFT HAND SIDE. You entered :'\" + calExpr + \"'\"; } rhs = calExpr.substring(calExpr.indexOf(\"*\") + 1, calExpr.length()); try { rhsInt = Integer.valueOf(rhs); } catch (Exception e) { output = \"Please enter valid RIGHT HAND SIDE. You entered :'\" + calExpr + \"'\"; } output = calExpr + \"=\" + (lhsInt * rhsInt); } else if (calExpr.contains(\"/\") && calExpr.indexOf(\"/\") > 0) { lhs = calExpr.substring(0, calExpr.indexOf(\"/\")); try { lhsInt = Integer.valueOf(lhs); } catch (Exception e) { output = \"Please enter valid LEFT HAND SIDE. You entered :'\" + calExpr + \"'\"; } rhs = calExpr.substring(calExpr.indexOf(\"/\") + 1, calExpr.length()); try { rhsInt = Integer.valueOf(rhs); } catch (Exception e) { output = \"Please enter valid RIGHT HAND SIDE. You entered :'\" + calExpr + \"'\"; } output = calExpr + \"=\" + (lhsInt / rhsInt); } } else output = \"Please enter the expression to calculate.\"; } DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); outToClient.writeBytes(output); outToClient.close(); } } catch (Exception e) { StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw); e.printStackTrace(pw); FileUtils.write(new File(\"ERROR.log\"), sw.toString()); } } }";
			FileUtils.write(new File("Logic.java"), newprogram);
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			{
				List<String> optionList = new ArrayList<String>();
				optionList.addAll(Arrays.asList("-classpath", System.getProperty("java.class.path")));
				DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
				StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null);
				Iterable<? extends JavaFileObject> compilationUnits = manager.getJavaFileObjects(new File("Logic.java"));
				compiler.getTask(null, manager, null, optionList, null, compilationUnits).call();
				manager.close();
			}
			Runtime.getRuntime().exec("cmd /C \"start java Logic");
			{
				String sentence;
				String modifiedSentence;
				try
				{
					Scanner scan = new Scanner(System.in);
					while (scan.hasNext())
					{
						Socket clientSocket = new Socket("localhost", 6789);
						DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
						BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						sentence = scan.nextLine();
						//
						outToServer.writeBytes(sentence + '\n');
						modifiedSentence = inFromServer.readLine();
						System.out.println("\t -> " + modifiedSentence);
						clientSocket.close();
					}
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
