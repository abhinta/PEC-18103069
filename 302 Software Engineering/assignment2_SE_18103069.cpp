/*Phone Directory
Code by: Abhinav Gupta, 18103069, CSE
This program doesn't store / fetch from files
Whatever data is stored is stored at runtime and destroys after program execution

Program Details
---------------

For contacts "Abhinav gupta" and "abhinav gupta" => Both are equal for program.
For program, a person changes only if the last name changes (when first name are same)
eg. "John Doe" and "John Doe" might be different people for user but, program will consider them as same person...which is quite obvious in practical world
So, user must save their contact names with different last name, eg. adding a numeral after last name, etc.

Maximum contact length can be 15 digits,
eg. in French Guiana
Country code : +594
Format : +594 700 XXX XXX XXX, excluding '+'

Features:
========

Storing a contact number is possible only if length >=10, Storing landline number alond with std code is a must

As per updation, user is reqd. to enter new contact no. (to replace with older one).
Program asks at runtime (linearly, i.e. corresponding to every phone no. of the person) for which replacement must be made
Application of this approach is simple: Nowadays, people don't really remember exact phone no. of other person
Program handles updation in cases of multiple phone no.s

As per the question, searching is possible on the basis of prefix of name
My code can even filter people with incomplete prefixes. eg, Searching all people with prefix characters = "Jo"

Also, if at deletion incomplete prefixes are used, program helps in suggesting user possible contacts.


Where My code won't work / Precautions
======------------------------========

1.There is a bug when a name has too many spaces
My code can handle single space separated name, eg. "John Doe" and not "John   Doe". Also, can handle "John ";
2.Updation of person's name isn't possible. Only contact can be altered (multiple phone no. cases considered). Although, code for this can be written. I just didn't becoz it would be too advance for this assignment
*/
#include <iostream>
#include <unordered_map>
#include <vector>
#include <utility>
using namespace std;
class PhoneDirectory;
class PersonInfo{
	string lastName;
	vector<string> contactList;
	friend PhoneDirectory;
};
class PhoneDirectory{
	unordered_map<string,vector<PersonInfo>> arr[26];
	bool validate_name(string name){
		if(name.length() < 1 || !( (name[0] >= 'a' && name[0] <= 'z') || (name[0] >= 'A' && name[0] <= 'Z') )  )
		return false;
	return true;
	}
	bool validate_contact(string contact){
		if(contact.length() > 15)
		return false;
		for(int i=0;i<contact.length();i++){
			if(contact[i] < 48 || contact[i] > 57)
				return false;
		}
		if(contact.length() < 10)
			return false;
	return true;
	}
	int getCharIntIndex(char ch){
			return ch-65;//'A' -> 0
	}
	string correctName(string name){
		for(int i=0;i<name.length();i++){
			if(name[i] >= 65 && name[i] <= 90)
			name[i] = name[i]+32;//'A' -> 'a'
		}
		// "JohN DOe" -> "john doe"
		int index = name.find(' ');

		string firstName = name;
		string lastName;
		if(index != -1){
			firstName = name.substr(0,index);
			lastName = name.substr(index+1);

			firstName = (char)(firstName[0]-32) + firstName.substr(1);
			lastName = (char)(lastName[0]-32) + lastName.substr(1);
		}
		else{
			firstName = (char)(firstName[0]-32) + firstName.substr(1);
		}
		//"john doe" -> "John Doe"
		return firstName + " " + lastName;
	}
	pair<string, string> separateFirstAndLastName(string name){
		pair<string, string> ans;
		ans.first = name;
		ans.second = "";
		int ind = name.find(' ');
		if(ind != -1){
			ans.first = name.substr(0,ind);
			ans.second = name.substr(ind+1);
		}

		return ans;
	}
	void print(string key){//always prints for valid keys(first name)

		int index = getCharIntIndex(key[0]);//"John Doe" will be stored at arr index = 9
		unordered_map<string,vector<PersonInfo>> &currentNameDirectory = arr[index];

		vector<PersonInfo> &vec = currentNameDirectory[key];

		for(int i=0;i<vec.size();i++){//searches for all "John"
			for(int j=0;j<vec[i].contactList.size();j++){
				if(vec[i].lastName != "")
				cout<<key<<"\t\t"<<vec[i].lastName<<"\t\t"<<vec[i].contactList[j]<<endl;
				else
				cout<<key<<"\t\t"<<"-"<<"\t\t"<<vec[i].contactList[j]<<endl;
			}
		}

	}
public:
	void create(string name, string contact){

		if(validate_name(name) == false)
		{
			cout<<"Enter Valid Name starting with a-z or A-Z"<<endl;
			return;
		}
		if(validate_contact(contact) == false)
		{
			cout<<"Enter Valid Contact number (15 digits max., excluding trunk prefix, '+')"<<endl;
			return;
		}
		name = correctName(name);//"JoHN DoE" -> "John Doe"
		string firstName,lastName;

		pair<string,string> p = separateFirstAndLastName(name);
		firstName = p.first;//"John"
		lastName = p.second;//"Doe"

		int index = getCharIntIndex(name[0]);//"John Doe" will be stored at arr index = 9
		unordered_map<string,vector<PersonInfo>> &currentNameDirectory = arr[index];
		if(currentNameDirectory.count(firstName) > 0){//searches if there exist a "John"
			vector<PersonInfo> &vec = currentNameDirectory[firstName];
			for(int i=0;i<vec.size();i++){//searches for all "John" + "last name"
				if(vec[i].lastName == lastName){
					for(int j=0;j<vec[i].contactList.size();j++){
						if(vec[i].contactList[j] == contact){
							cout<<"Contact already exists with this name and no."<<endl;
							return;
						}
					}
					cout<<contact<<" inserted for "<<firstName<<" "<<lastName<<endl;
					vec[i].contactList.push_back(contact);
				return;
				}
			}
			PersonInfo obj;
			obj.lastName = lastName;
			obj.contactList.push_back(contact);
			vec.push_back(obj);
			cout<<firstName<<" "<<lastName<<" ( "<<contact<<" ) inserted successfully!"<<endl;

		}
		else{

			vector<PersonInfo> vec;
			PersonInfo obj;
			obj.lastName = lastName;
			obj.contactList.push_back(contact);
			vec.push_back(obj);
			currentNameDirectory[firstName] = vec;

			cout<<firstName<<" "<<lastName<<" inserted successfully!"<<endl;
		}

	}

	void updateContact(string name, string contact){
		if(validate_name(name) == false)
		{
			cout<<"Enter Valid Name starting with a-z or A-Z"<<endl;
			return;
		}
		if(validate_contact(contact) == false)
		{
			cout<<"Enter Valid Contact number (15 digits max., excluding trunk prefix, '+')"<<endl;
			return;
		}
		name = correctName(name);//"JoHN DoE" -> "John Doe"
		string firstName,lastName;

		pair<string,string> p = separateFirstAndLastName(name);
		firstName = p.first;//"John"
		lastName = p.second;//"Doe"

		int index = getCharIntIndex(name[0]);//"John Doe" will be stored at arr index = 9
		unordered_map<string,vector<PersonInfo>> &currentNameDirectory = arr[index];
		if(currentNameDirectory.count(firstName) > 0){//searches if there exist a "John"

			vector<PersonInfo> &vec = currentNameDirectory[firstName];
			bool NotFound = true;
			for(int i=0;i<vec.size();i++){//searches for all "John" + "last name"
				if(vec[i].lastName == lastName){

					for(int j=0;j<vec[i].contactList.size();j++){
						int option = 1;
						cout<<"Is this the contact ("<<vec[i].contactList[j]<<") you want to replace with "<<contact<<" ?\n1:YES\n2:NO\nAny other option: Abort\n";
						cin>>option;
						bool bad;
						bad = cin.fail();
						if(bad){
							cout<<"Updation Aborted"<<endl;
							std::cin.clear();
                            std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
							return;
						}
						if(option == 1){
							cout<<firstName<<" "<<lastName<<" "<<"updated from "<<vec[i].contactList[j];
							vec[i].contactList[j] = contact;
							cout<<" to "<<vec[i].contactList[j]<<endl;
							return;
						}
						else if(option == 2)
							continue;
						else
							return;
					}

					if(NotFound)
                        cout<<"No Updation Done"<<endl;

					return;

				}
			}

			if(NotFound)//works in either case, if name doesn't exist
				cout<<"Contact not found"<<endl;
		}
		else
			cout<<"No such name exist is record"<<endl;
	}

	void searchContact(string name){
		if(validate_name(name) == false)
		{
			cout<<"Enter Valid Name starting with a-z or A-Z"<<endl;
			return;
		}
		name = correctName(name);//"JoHN DoE" -> "John Doe"
		string firstName,lastName;

		pair<string,string> p = separateFirstAndLastName(name);
		firstName = p.first;//"John"
		lastName = p.second;//"Doe"

		int index = getCharIntIndex(name[0]);//"John Doe" will be stored at arr index = 9
		unordered_map<string,vector<PersonInfo>> &currentNameDirectory = arr[index];
		if(currentNameDirectory.count(firstName) > 0){//searches if there exist a "John"

			vector<PersonInfo> &vec = currentNameDirectory[firstName];
			if(lastName.length() == 0){//i.e. it is ""
				cout<<"First Name"<<"\tLast Name"<<"\tContact No."<<endl;
				cout<<"-------------------------------------"<<endl;
				print(firstName);
				cout<<endl;
			}
			else{
				for(int i=0;i<vec.size();i++){//searches for all "John" + "last name"
					if(vec[i].lastName == lastName){
						if(vec[i].contactList.size() != 0){
							cout<<"First Name"<<"\tLast Name"<<"\tContact No."<<endl;
							cout<<"-----------------------------------"<<endl;
						}
						for(int j=0;j<vec[i].contactList.size();j++){
							cout<<firstName<<"\t\t"<<lastName<<"\t\t"<<vec[i].contactList[j]<<endl;
						}
					cout<<endl;
					return;
					}
				}
			}

		}
		else{
			//say the user searches for incomplete prefixes, eg."Jo" instead of "John"
			if(lastName == ""){
				bool anyRecordFound = false;

				for(auto p: currentNameDirectory){
					string key = p.first;

					if(firstName == key.substr(0,firstName.length()))//works only if length(firstName) <= length(key)
					{
						if(anyRecordFound == false){//this whole thing will execute only once
							cout<<"No Person exists with the name "<<firstName
							<<". Here are some suggestions you might be looking for:"<<endl;
							cout<<"First Name"<<"\tLast Name"<<"\tContact No."<<endl;
							cout<<"-----------------------------------"<<endl;
							anyRecordFound = true;
						}
						print(key);
					}
				}

				if(anyRecordFound == false)
				cout<<"No such name exist is record"<<endl;
				else
				cout<<endl;
			}
			else//"Jo Doe" won't print anything
				cout<<"No such name exist is record"<<endl;

		}
	}

	void deleteContact(string name, string contact){
		if(validate_name(name) == false)
		{
			cout<<"Enter Valid Name starting with a-z or A-Z"<<endl;
			return;
		}
		if(validate_contact(contact) == false)
		{
			cout<<"Enter Valid Contact number (15 digits max., excluding trunk prefix, '+')"<<endl;
			return;
		}
		name = correctName(name);//"JoHN DoE" -> "John Doe"
		string firstName,lastName;

		pair<string,string> p = separateFirstAndLastName(name);
		firstName = p.first;//"John"
		lastName = p.second;//"Doe"

		int index = getCharIntIndex(name[0]);//"John Doe" will be stored at arr index = 9
		unordered_map<string,vector<PersonInfo>> &currentNameDirectory = arr[index];
		if(currentNameDirectory.count(firstName) > 0){//searches if there exist a "John"
			vector<PersonInfo> &vec = currentNameDirectory[firstName];
			for(int i=0;i<vec.size();i++){//searches for all "John" + "last name"
				if(vec[i].lastName == lastName){
					for(int j=0;j<vec[i].contactList.size();j++){
						if(vec[i].contactList[j] == contact){

							cout<<contact<<" deleted successfully for "<<firstName<<" "<<lastName<<endl;
							for(int k = j; k < vec[i].contactList.size()-1; k++){
								vec[i].contactList[k] = vec[i].contactList[k+1];
							}
							vec[i].contactList.pop_back();
							return;
						}
					}
				cout<<"This contact number doesn't exist for "<<firstName<<" "<<lastName<<endl;
				return;
				}
			}
			//for cases, eg. "John", "John LtNm" where John LtNm doesn't exist but, John Doe, John Soe exists
			cout<<"No such contact no. exists with the name "<<firstName<<" "<<lastName<<endl;

			bool suggestion = false;
			int top = 1;
			for(int i=0;i<vec.size();i++){//searches for all "John" + "last name"
				if(vec[i].contactList.size() != 0)
				{
					if(suggestion == false)
					{
						cout<<"Try being precise with last name\nHere are some suggestions'"<<endl;
						suggestion = true;
					}
					cout<<top<<". "<<firstName<<" "<<vec[i].lastName<<endl;
					top++;
				}
			}
			if(suggestion == false){
				cout<<"Sorry no suggestions detected"<<endl;
			}

		}
		else
			cout<<"No such contact exists"<<endl;
	}

};

int main()
{

	PhoneDirectory obj;

	int choice;
	cout
	<<"Create Contact (press 1)"<<endl
	<<"Update Contact (press 2)"<<endl
	<<"Delete Contact (press 3)"<<endl
	<<"Search Contact (press 4)"<<endl
	<<"Exit (press 0)"<<endl;
	cin>>choice;
	bool bad;
	bad = cin.fail();
	while(bad){
		cout<<"Request Aborted. Re-enter choice(1,2,3,4,0):";
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
		cin>>choice;
		bad = cin.fail();
	}

	while( choice != 0){
		fflush(stdin);
		switch(choice){//no request being 0 can enter this
			case 1:
			{
				string name, contact;
				cout<<"Enter Name: ";
				getline(cin,name);
				cout<<"Enter Contact: ";
				getline(cin,contact);
				obj.create(name,contact);
				break;
			}
			case 2:
			{
				string name, contact;
				cout<<"Enter Name: ";
				getline(cin,name);
				cout<<"Enter New Contact (which will replace older one): ";
				getline(cin,contact);
				obj.updateContact(name,contact);
				break;
			}
			case 3:
			{
				string name, contact;
				cout<<"Enter Name: ";
				getline(cin,name);
				cout<<"Enter Contact you want to delete: ";
				getline(cin,contact);
				obj.deleteContact(name,contact);
				break;
			}
			case 4:
			{
				string name, contact;
				cout<<"Enter Name: ";
				getline(cin,name);
				obj.searchContact(name);
				break;
			}
			default:
			cout<<"Wrong Choice. Re-enter"<<endl;
		}
		cout
			<<"Create Contact (press 1)"<<endl
			<<"Update Contact (press 2)"<<endl
			<<"Delete Contact (press 3)"<<endl
			<<"Search Contact (press 4)"<<endl
			<<"Exit (press 0)"<<endl;
		cin>>choice;
		bad = cin.fail();
		while(bad){
			cout<<"Request Aborted. Re-enter choice(1,2,3,4,0):";
			std::cin.clear();
			std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');
			cin>>choice;
			bad = cin.fail();
		}
	}



	return 0;
}

/*sample test cases
// obj.create("John Doe","98");
// obj.create("John Doe","9876543202");
// obj.create("John Doe","9876543203");
// obj.create("John Foe","9876543204");
// obj.create("John Foe","9876543204");
// obj.create("John Foe","9876543209");
// obj.print("John");
// obj.print("Dohn");


// obj.updateContact("John","1234567890");
// obj.searchContact("S");
// obj.searchContact("Sn");
// obj.searchContact("Si");
// obj.searchContact("John");


// obj.create("John Doe","9876543201");
// obj.create("John Doe","9876543202");
// obj.create("John Soe","9876543203");
// obj.create("John Soe","9876543204");
// obj.create("Kohn Goe","9876543205");
// obj.create("Kohn Goe","5876543206");
// obj.create("Jim Moe","5876543207");
// obj.create("Jim Moe","5876543207");

// cout<<"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"<<endl;
// obj.deleteContact("John Doe", "9876543201");
// obj.deleteContact("John", "9876543201");

*/
