import java.io.IOException;

/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
	public static void main(String[] args) throws Exception {
		// Profile p = new Profile("Fernando","1234");
		PProfile p = new PProfile("https//:test.com", "user1", "test12345");

		System.out.println(p);
	}
}
