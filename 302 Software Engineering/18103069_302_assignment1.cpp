/*
Lab Assignment 1: Write a program which accepts the three sides of a triangle and thus decides which type of triangle the sides will make.
Abhinav Gupta_18103069
*/
#include<iostream>
using namespace std;
int main(){
	float a,b,c;
	bool bad = false;
	cin >> a >> b >> c;

	bad = cin.fail();
	//checking if datatype is correct
	if (bad)
		cout << "Enter Valid lengths (Float values allowed)" << endl;
	else{

		//checking if lengths are positive and
		//checking if triangle can be formed
		if(a+b>c && b+c>a && a+c>b && a>0 && b>0 && c>0){
	
			if(a==b || b==c || a==c){
				//isosceles confirmed
				if(a==b && b==c)
					cout<<"Equilateral Triangle"<<endl;
				else
					cout<<"Isosceles Triangle"<<endl;
			}
			else{
				cout<<"Scalene Triangle"<<endl;
			}
		}
		//either side length is negative or sum property of triangle isn't satisfied
		else
			cout<<"Triangle will not form with given data"<<endl;
	}
}