import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.FileUtils;

public class Logic
{
	public static void main(String[] args) throws IOException
	{
		try
		{
			String clientSentence;
			ServerSocket welcomeSocket = new ServerSocket(6789);
			while (true)
			{
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				clientSentence = inFromClient.readLine();
				System.out.println(clientSentence);
				String input = clientSentence, output = "";
				{
					if (null != input && input.length() > 0 && null != input)
					{
						final String calExpr = input;
						int lhsInt = 0, rhsInt = 0;
						String lhs = null, rhs = null;
						if (calExpr.contains("++") && calExpr.indexOf("++") > 0)
						{
							lhs = calExpr.substring(0, calExpr.indexOf("++"));
							try
							{
								lhsInt = Integer.valueOf(lhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid LEFT HAND SIDE. You entered :'" + calExpr + "'";
							}
							output = calExpr + "=" + ++lhsInt;
						}
						else if (calExpr.contains("--") && calExpr.indexOf("--") > 0)
						{
							lhs = calExpr.substring(0, calExpr.indexOf("--"));
							try
							{
								lhsInt = Integer.valueOf(lhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid LEFT HAND SIDE. You entered :'" + calExpr + "'";
							}
							output = calExpr + "=" + --lhsInt;
						}
						else if (calExpr.contains("+") && calExpr.indexOf("+") > 0)
						{
							lhs = calExpr.substring(0, calExpr.indexOf("+"));
							try
							{
								lhsInt = Integer.valueOf(lhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid LEFT HAND SIDE. You entered :'" + calExpr + "'";
							}
							rhs = calExpr.substring(calExpr.indexOf("+") + 1, calExpr.length());
							try
							{
								rhsInt = Integer.valueOf(rhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid RIGHT HAND SIDE. You entered :'" + calExpr + "'";
							}
							output = calExpr + "=" + (lhsInt + rhsInt);
						}
						else if (calExpr.contains("*") && calExpr.indexOf("*") > 0)
						{
							lhs = calExpr.substring(0, calExpr.indexOf("*"));
							try
							{
								lhsInt = Integer.valueOf(lhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid LEFT HAND SIDE. You entered :'" + calExpr + "'";
							}
							rhs = calExpr.substring(calExpr.indexOf("*") + 1, calExpr.length());
							try
							{
								rhsInt = Integer.valueOf(rhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid RIGHT HAND SIDE. You entered :'" + calExpr + "'";
							}
							output = calExpr + "=" + (lhsInt * rhsInt);
						}
						else if (calExpr.contains("/") && calExpr.indexOf("/") > 0)
						{
							lhs = calExpr.substring(0, calExpr.indexOf("/"));
							try
							{
								lhsInt = Integer.valueOf(lhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid LEFT HAND SIDE. You entered :'" + calExpr + "'";
							}
							rhs = calExpr.substring(calExpr.indexOf("/") + 1, calExpr.length());
							try
							{
								rhsInt = Integer.valueOf(rhs);
							}
							catch (Exception e)
							{
								output = "Please enter valid RIGHT HAND SIDE. You entered :'" + calExpr + "'";
							}
							output = calExpr + "=" + (lhsInt / rhsInt);
						}
					}
					else
						output = "Please enter the expression to calculate.";
				}
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				outToClient.writeBytes(output);
				outToClient.close();
			}
		}
		catch (Exception e)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			FileUtils.write(new File("ERROR.log"), sw.toString());
		}
	}
}
