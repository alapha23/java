import java.util.*;

class lab9
{public static void main(String []args){}}

class Foo
{
	int fo;
	Foo(){fo=1;}
	public void foo_1(){}
	public void foo_2(){}
	public void foo(){return ;}

}

class Bar extends Foo
{
	Bar(){}
	public void bar_2(){}
	public void foo(){}
}

class Baz extends Foo
{
	Baz(){super();}
	public void baz_1(){}
	public void baz(){}
}

class Mumble extends Baz
{
	Mumble(){super();}
	public void baz_1(){}
	public void mumble_2(){}
	public void baz(){}

}
