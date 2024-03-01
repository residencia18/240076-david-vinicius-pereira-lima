#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;


class Data {
	int dia, mes, ano;
	public:
	
	/*
	O m�todo abaixo retornar� -1 se d1 � anterior a d2
	Retornar� 0 se d1 = d2
	Retornar� +1 se d1 � posterior a d2
	*/	
	static int compara(Data d1, Data d2) { 
		if(d1.ano == d2.ano && d1.mes == d2.mes && d1.dia == d2.dia){
			return 0;
		}
		else if(d1.ano > d2.ano || d1.mes > d2.mes || d1.dia > d2.dia){
			return 1;
		}
		else{
			return -1;	
		}
	}
	
	Data (int _dia, int _mes, int _ano) {
		dia = _dia;
		mes = _mes;
		ano = _ano;
	}

	string toString() {
		string ret = "";
		ret.append(to_string(dia));
		ret.append("/");
		ret.append(to_string(mes));
		ret.append("/");
		ret.append(to_string(ano));
		return ret;
	}
};

class Lista {
	public:
	virtual void entradaDeDados() =0;
	virtual void mostraMediana() =0;
	virtual void mostraMenor() =0;
	virtual void mostraMaior() =0;
};

class ListaNomes : public Lista{
	vector<string> lista;
	
	public:
	
	/*
	O m�todo abaixo pergunta ao usu�rios quantos
	elementos v�o existir na lista e depois
	solicita a digita��o de cada um deles
	*/	
	void entradaDeDados() override{
		int quant;string nome;
		cout<<"Quantos elementos tera na lista de nomes? : ";cin>>quant;
		for(int i=0;i<quant;i++){
			cout<<"Digite o "<<i+1<<" nome: ";cin>>nome;
			lista.push_back(nome);
		}
		sort(lista.begin(), lista.end());
	}
		
	void mostraMediana() {
		cout << "Aqui vai mostrar a mediana da lista de strings" << endl;
		
		if(lista.size()%2 == 0){
			cout<<"Mediana: "<<lista[(lista.size()/2)-1]<<endl;
		}
		else{
			cout<<"Mediana: "<<lista[(lista.size()/2)]<<endl;
		}
	}
	
	void mostraMenor() {
		string menor;
		cout << "Aqui vai mostrar o primeiro nome alfabeticamente" << endl;
		cout<<"Menor: "<<lista[0]<<endl;
	}
	void mostraMaior() {
		string maior;
		cout << "aqui vai mostrar o ultimo nome alfabeticamente" << endl;
		cout<<"Maior: "<<lista[lista.size()-1]<<endl;
	}

	void listarEmOrdem(){
		cout<<"Listando em nomes em ordem: "<<endl;
		for(auto l:lista){
			cout<<l<<endl;
		}
	}

	void primeirosElementos(int n){
		if(!this->lista.empty()){	
			cout<<"Listando os "<<n<<" primeiros elementos: "<<endl;
			for(int i=0;i<n;i++){
				cout<<lista[i]<<endl;
			}
		}
		else{
			cout<<"Lista vazia"<<endl;
		}
	}
};

class ListaDatas :public Lista {
	vector<Data> lista;
	
	public:
		
	/*
	O m�todo abaixo pergunta ao usu�rios quantos
	elementos v�o existir na lista e depois
	solicita a digita��o de cada um deles
	*/	
	void entradaDeDados() {
		int quant;
		int dia,mes,ano;
		cout<<"Quantos elementos tera na lista de datas? : ";cin>>quant;
		for(int i=0;i<quant;i++){
			cout<<"Digite o dia da "<<i+1<<" data: ";cin>>dia;
			cout<<"Digite o mes da "<<i+1<<" data: ";cin>>mes;
			cout<<"Digite o ano da "<<i+1<<" data: ";cin>>ano;
			Data data(dia,mes,ano);
			lista.push_back(data);
		}
	}
	
	void mostraMediana() {
		cout << "Aqui vai mostrar a mediana da lista de datas" << endl;
		if(lista.size()%2 == 0){
			cout<<"Mediana: "<<lista[(lista.size()/2)-1].toString()<<endl;
		}
		else{
			cout<<"Mediana: "<<lista[(lista.size()/2)].toString()<<endl;
		}
	}
	
	void mostraMenor() {
		cout << "Aqui vai mostrar a primeira data cronologicamente" << endl;
		for(auto l:lista){
			if(Data::compara(l,lista[0]) == -1){
				cout<<"Menor: "<<l.toString()<<endl;
			}
		}
	}
	void mostraMaior() {
		cout << "aqui vai mostrar a ultima data cronologicamente" << endl;
		for(auto l:lista){
			if(Data::compara(l,lista[0]) == 1){
				cout<<"Maior: "<<l.toString()<<endl;
			}
		}
	}
};

class ListaSalarios :public Lista {
	vector<float> lista;
	
	public:
	
	/*
	O m�todo abaixo pergunta ao usu�rios quantos
	elementos v�o existir na lista e depois
	solicita a digita��o de cada um deles
	*/	
	void entradaDeDados() {
		int quant;float salario;
		cout<<"Quantos elementos tera na lista?"<<endl;cin>>quant;
		for(int i=0;i<quant;i++){
			cout<<"Digite o salario: "<<endl;cin>>salario;
			lista.push_back(salario);
		}
	}
			
	void mostraMediana() {
		cout << "Aqui vai mostrar a mediana da lista de salarios" << endl;
		if(lista.size()%2 == 0){
			cout<<"Mediana: "<<lista[lista.size()/2]<<endl;
		}
		else{
			cout<<"Mediana: "<<lista[(lista.size()/2)+1]<<endl;
		}
	}
	
	void mostraMenor() {
		cout << "Aqui vai mostrar o menor dos salarios" << endl;
		for(auto l:lista){
			if(l < lista[0]){
				cout<<"Menor: "<<l<<endl;
			}
		}
	}
	void mostraMaior() {
		cout << "aqui vai mostrar o maior dos salarios" << endl;
		for(auto l:lista){
			if(l > lista[0]){
				cout<<"Maior: "<<l<<endl;
			}
		}
	}
};


class ListaIdades : public Lista {
	vector<int> lista;
	
	public:
		
		/*
	O m�todo abaixo pergunta ao usu�rios quantos
	elementos v�o existir na lista e depois
	solicita a digita��o de cada um deles
	*/	
	void entradaDeDados() {
		int qunt,idade;
		cout<<"Quantos elementos tera na lista?"<<endl;cin>>qunt;
		for(int i=0;i<qunt;i++){
			cout<<"Digite a idade: "<<endl;cin>>idade;
			lista.push_back(idade);
		}
	}
	
	void mostraMediana() {
		cout << "Aqui vai mostrar a mediana da lista de idades" << endl;
		if(lista.size()%2 == 0){
			cout<<"Mediana: "<<lista[lista.size()/2]<<endl;
		}
		else{
			cout<<"Mediana: "<<lista[(lista.size()/2)+1]<<endl;
		}
	}
	
	void mostraMenor() {
		cout << "Aqui vai mostrar a menor das idades" << endl;
		for(auto l:lista){
			if(l < lista[0]){
				cout<<"Menor: "<<l<<endl;
			}
		}

	}
	void mostraMaior() {
		cout << "aqui vai mostrar a maior das idades" << endl;
		for(auto l:lista){
			if(l > lista[0]){
				cout<<"Maior: "<<l<<endl;
			}
		}
	}
};
 
int main () {
	vector<Lista*> listaDeListas;
	
	ListaNomes listaNomes;
	listaNomes.entradaDeDados();
	listaDeListas.push_back(&listaNomes);
	
	ListaDatas listaDatas;
	listaDatas.entradaDeDados();
	listaDeListas.push_back(&listaDatas);
	
	ListaSalarios listaSalarios;
	listaSalarios.entradaDeDados();
	listaDeListas.push_back(&listaSalarios);
	
	ListaIdades listaIdades;
	listaIdades.entradaDeDados();
	listaDeListas.push_back(&listaIdades);
	
	for (Lista* l : listaDeListas) {
		l->mostraMediana();
		l->mostraMenor();
		l->mostraMaior();
	}
	
}
    

