package admi.newview.view;

import android.util.Log;

import java.util.ArrayList;

public class ViewRefreshUtil implements Runnable {
	private Thread thread;
	private static ViewRefreshUtil viewRefreshUtil;
	private ArrayList<ViewRefreshInterfaceEntity> viewRefreshInterfaces;
	
	public static ViewRefreshUtil getRefreshUtil() {
		if (null == viewRefreshUtil) {
			viewRefreshUtil = new ViewRefreshUtil();
		}
		return viewRefreshUtil;
	}
	
	private ViewRefreshUtil() {
		viewRefreshInterfaces = new ArrayList<ViewRefreshInterfaceEntity>();
	}
	
	public ViewRefreshInterfaceEntity addViewRefreshInterface(ViewRefreshInterface viewRefreshInterface) {
		ViewRefreshInterfaceEntity viewRefreshInterfaceEntity = new ViewRefreshInterfaceEntity();
		viewRefreshInterfaceEntity.setViewRefreshInterface(viewRefreshInterface);
		viewRefreshInterfaces.add(viewRefreshInterfaceEntity);
		return viewRefreshInterfaceEntity;
	}
	
	public void startViewRefresh(ViewRefreshInterfaceEntity viewRefreshInterfaceEntity) {
		viewRefreshInterfaceEntity.setRefresh(true);
		if (null == thread || !thread.isAlive()) {
			thread = new Thread(this);
            thread.start();
		}
	}
	
	public void stopViewRefresh(ViewRefreshInterfaceEntity viewRefreshInterfaceEntity) {
		viewRefreshInterfaceEntity.setRefresh(false);
	}
	
	public interface ViewRefreshInterface {
		void refresh();
	}
	
	@Override
	public void run() {
		int i = 1;
        while (i != 0) {
            i=0;
            for (ViewRefreshInterfaceEntity viewRefreshInterfaceEntity : viewRefreshInterfaces) {
                if (viewRefreshInterfaceEntity.isRefresh()) {
                	viewRefreshInterfaceEntity.getViewRefreshInterface().refresh();
                    i++;

                }

            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			Log.d("select",i+"");
        }
	}
	
	public class ViewRefreshInterfaceEntity {
		private boolean isRefresh = false;
		private ViewRefreshInterface viewRefreshInterface;

		public boolean isRefresh() {
			return isRefresh;
		}

		public void setRefresh(boolean isRefresh) {
			this.isRefresh = isRefresh;
		}

		public ViewRefreshInterface getViewRefreshInterface() {
			return viewRefreshInterface;
		}

		public void setViewRefreshInterface(ViewRefreshInterface viewRefreshInterface) {
			this.viewRefreshInterface = viewRefreshInterface;
		}

	}

}