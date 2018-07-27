package com.demo.ws;


import javax.jws.WebService;


@WebService
public class HelloServiceImpl implements HelloService{

	@Override
	public String say(String name) {
		// TODO Auto-generated method stub
		return "hello: "+name;
	}

}
