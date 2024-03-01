//Cabeçalho das classes

#ifndef CLASSES_HPP
#define CLASSES_HPP

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
        bool dataValida(void);
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
        bool horaValida(void);
        bool horaValida(int h, int m, int s);
        void printHora(void);
        string toString(void);
};

class DataHora{
    private:
        Data data;
        Hora hora;
    
    public:
        DataHora();
        DataHora(Data data, Hora Hora);  
        Data getData(void);
        void setData(Data data);
        Hora getHora(void);
        void setHora(Hora hora);
        void printDataHora(void);
        bool verificaDataHora(void);
        bool verificaDataHora(Data data, Hora hora);
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
        string converteCpf(void);
        string converteCpf(string cpf);
        bool validaNome(string nome);
        bool validaCpf();
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
        Paciente * obterPaciente(string cpf);
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
        bool validaCrm();
        bool validaCrm(string crm);
        bool validaNome(string nome);
        void formataCrm(void);
};

class Medicos{
    private:
        vector<Medico *> lista_medicos;

    public:
        void insereMedico(Medico *medico);
        void alterarMedico(Medico * medico);
        void excluirMedico(Medico * medico);
        vector<Medico*> obterListaMedicos(void);
        bool verificaExiste(string crm);
        Medico *obterMedico(string crm);
};

class Consulta{
    private:
        bool realizada;
        DataHora data_hora;
        Hora duracao;
        string convenio;
        Paciente * paciente;
        Medico * medico;

    public:
        Consulta();
        Consulta(DataHora data_hora, Hora duracao, string convenio, Paciente * paciente, Medico * medico);
        bool getRealizada(void);
        void setRealizada(bool realizada);
        DataHora getDataHora(void);
        void setDataHora(DataHora data_hora);
        Hora getDuracao(void);
        void setDuracao(Hora duracao);
        string getConvenio(void);
        void setConvenio(string convenio);
        Paciente * getPaciente(void);
        void setPaciente(Paciente * paciente);
        Medico * getMedico(void);
        void setMedico(Medico * medico);
        void printConsulta(void);
};

class Consultas{
    private:
        vector<Consulta*> lista_consultas;

    public:
        void insereConsulta(Consulta * consulta);
        void alterarConsulta(Consulta * consulta);
        void excluirConsulta(Consulta * consulta);
        vector<Consulta*> obterListaConsultas(void);
};

#endif