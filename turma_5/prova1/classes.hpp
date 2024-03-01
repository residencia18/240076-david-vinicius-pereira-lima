//implementacao das classes
#include<iostream>
#include<vector>
#include<string>
#include<cctype>

using namespace std;

class Data{
    private:
        int dia;
        int mes;
        int ano;

    public:
        Data();
        Data(int dia, int mes, int ano);
        void setDia(int dia);
        void setMes(int mes);
        void setAno(int ano);
        int getDia(void);
        int getMes(void);
        int getAno(void);
        bool dataValida(int dia, int mes, int ano);
        string toString(void);
        void printData(void);
};

class Hora{
    private:
        int hora;
        int minuto;
        int segundo;

    public:
        Hora();
        Hora(int h, int m, int s);
        void setHora(int hora);
        void setMinuto(int minuto);
        void setSegundo(int segundo);
        int getHora(void);
        int getMinuto(void);
        int getSegundo(void);
        bool horaValida(int h, int m, int s);
        void printHora(void);
};

class Paciente{
    private:
        string cpf;
        string nome;
        Data data_nasc;

    public:
        Paciente();
        Paciente(string cpf, string nome, Data data_nasc);
        void setCpf(string cpf);
        void setNome(string nome);
        void setDataNasc(Data data_nasc);
        string getCpf(void);
        string getNome(void);
        Data getDataNasc(void);
        void printPaciente(void);
        //unsigned long long int converteCpf(void);
        //unsigned long long int converteCpf(string cpf);
        string converteCpf(void);
        string converteCpf(string cpf);
        bool validaNome(string nome);
        bool validaCpf(string cpf);
        void formataCpf(void);
};

class Pacientes{
    private:
        vector<Paciente*> lista_pacientes;

    public:
        void inserePaciente(Paciente *paciente);
        void alterarPaciente(Paciente * paciente);
        void excluirPaciente(Paciente * paciente);
        vector<Paciente*> obterListaPacientes(void);
        bool verificaExiste(string cpf);
};

class Medico{
    private:
        string crm;
        string nome;
        string especialidade;
    public:
        Medico();
        Medico(string crm, string nome, string especialidade);
        void setCrm(string crm);
        void setNome(string nome);
        void setEspecialidade(string especialidade);
        string getCrm(void);
        string getNome(void);
        string getEspecialidade(void);
        void printMedico(void);
        string converteCrm(void);
        string converteCrm(string crm);
        bool validaCrm(string crm);
        bool validaNome(string nome);
        void formataCrm(void);
};

class Medicos{
    private:
        vector<Medico*> lista_medicos;

    public:
        void insereMedico(Medico *medico);
        void alterarMedico(Medico * medico);
        void excluirMedico(Medico * medico);
        vector<Medico*> obterListaMedicos(void);
        bool verificaExiste(string crm);
};

