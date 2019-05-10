/*******************************************************************************
Name: Sarah Redmon
Date: 3/19/19
Instructor: Ms. Tucker
Class: InvalidPWException
Purpose: Sets up the exception object with a particular message.
*******************************************************************************
*/

public class InvalidPWException extends Exception
{
   InvalidPWException (String message)
   {
      super(message);
   }
}