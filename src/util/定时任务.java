package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class 定时任务 {

	public static Long oneDay = (long) 1 * 1000;

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {
				System.out.println("Runnable()");
				long curren = System.currentTimeMillis();
				curren -= 3 * 60 * 60 * 1000;
				Date da = new Date(curren);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(dateFormat.format(da));
				定时任务 dqwj = new 定时任务();
				// dqwj.main(args);

			}

		}, 0, oneDay, TimeUnit.MILLISECONDS);
	}
}
