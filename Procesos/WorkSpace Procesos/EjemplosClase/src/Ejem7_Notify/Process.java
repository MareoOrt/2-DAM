package Ejem7_Notify;

public class Process extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		for (int i = 0; i < 1999; i++) {
			System.out.println("Proceso: " + getName() + " numero " + i + " numero de procesos activos "
					+ getThreadGroup().activeCount());
		}
	}
}
