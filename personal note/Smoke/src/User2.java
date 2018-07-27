
public class User2 extends User{

	public User2(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		System.out.println("User2 exe!");
	}

	public static void main(String ...args)
	{
		Mediator m = new MyMediator();
		m.createMediator();
		m.workAll();
	}
}
