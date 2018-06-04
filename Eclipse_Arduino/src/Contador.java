public class Contador extends Thread{
		int time;
		MainApp main;
		
		public Contador(int time){
			super();
			this.time=time;
		}
		
		public void run(){
			while (time != 0){
				try {
					time--;
					System.out.println(time);
					sleep(1000);
					
					
					
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}
		
		
		
	}