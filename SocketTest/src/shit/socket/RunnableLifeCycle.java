package shit.socket;

public abstract class RunnableLifeCycle implements LifeCycle, Runnable {
	
	/**
	 * 线程
	 */
	Thread threadSelf;
	
	/**
	 * 
	 */
	boolean inited = false;

	/**
	 * 启停标志
	 */
	protected boolean flag = false;
	
	public RunnableLifeCycle() {
		super();
	}

	@Override
	public void close() {
		inited = false;
		flag = false;
		closeInternal();
	}

	@Override
	public void start() {
		if (!inited) {
			init();
		}
		flag = true;
		startInternal();
		threadSelf.start();
	}

	@Override
	public void init() {
		threadSelf = new Thread(this);
		initInternal();
		inited = true;
	}

	protected abstract void startInternal();

	protected abstract void closeInternal();

	protected abstract void initInternal();

	protected abstract void runInternal();
}
