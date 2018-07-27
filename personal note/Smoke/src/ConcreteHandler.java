
public class ConcreteHandler extends Handler{

	
	@Override
	public void handleRequest() {
		// TODO Auto-generated method stub
		if(getSuccessor()!=null)
		{
			System.out.println("Skip Request");
			getSuccessor().handleRequest();
		}
		else
		{
			System.out.println("Process Request");
		}
	}
	

}
