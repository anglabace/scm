package com.zskang.util;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
	private static final int N = 10;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch doneSingal = new CountDownLatch(N);
		CountDownLatch startSingal = new CountDownLatch(1);
		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(i, doneSingal, startSingal)).start();
		}
		System.out.println("------------------start------------");
		startSingal.countDown();
		doneSingal.await();
		System.out.println("ok!");
	}

	static class Worker implements Runnable {
		private int beginIndex = 0;
		private final CountDownLatch doneSingal;
		private final CountDownLatch startSingal;

		Worker(int beginIndex, CountDownLatch downSingal,
				CountDownLatch startSingal) {
			this.beginIndex = beginIndex;
			this.doneSingal = downSingal;
			this.startSingal = startSingal;
		}

		public void run() {
			try {
				startSingal.await();
				beginIndex = (beginIndex - 1) * 10 + 1;
				for (int i = beginIndex; i <= beginIndex+10; i++) {
					System.out.println(i);
				}

			} catch (Exception w) {
				w.printStackTrace();
			} finally {
				doneSingal.countDown();
			}
		}
	}

}
