import axios from 'axios';

export default class CadastroViewModel {
    constructor() {
        this.name = '';
        this.birthdate = '';
        this.phone = '';
        this.selectedState = '';
        this.selectedCity = '';
        this.email = '';
        this.password = '';
        this.confirmation = '';
        this.endereco = '';
        this.states = [];
        this.cities = [];
    }

    async fetchStates() {
        try {
            const response = await axios.get('https://servicodados.ibge.gov.br/api/v1/localidades/estados');
            this.states = response.data;
        } catch (error) {
            console.error('Erro ao buscar estados:', error);
        }
    }

    async fetchCities() {
        try {
            if (this.selectedState) {
                const response = await axios.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${this.selectedState}/municipios`);
                this.cities = response.data;
            }
        } catch (error) {
            console.error('Erro ao buscar cidades:', error);
        }
    }

    async submitForm() {
        // Verificar se os campos obrigatórios estão preenchidos
        if (!this.name || !this.email || !this.password || !this.confirmation || !this.selectedCity || !this.selectedState) {
            alert('Preencha todos os campos obrigatórios.');
            return;
        }

        // Verificar se as senhas são iguais
        if (this.password !== this.confirmation) {
            alert('As senhas não coincidem.');
            return;
        }

        // Preparar os dados do usuário
        const userData = {
            nomeCompleto: this.name,
            dataNascimento: this.birthdate,
            endereco: this.endereco,
            cidade: this.selectedCity,
            uf: this.selectedState,
            email: this.email,
            telefone: this.phone,
            senha: this.password,
        };

        try {
            console.log('Enviando dados do usuário:', userData);

            // Substitua pela URL correta do seu backend
            const response = await axios.post('http://localhost:8080/api/usuario', userData);

            if (response.status === 200) {
                alert("Usuário cadastrado com sucesso!");
            } else {
                alert('Erro ao cadastrar usuário. Tente novamente.');
            }
        } catch (error) {
            console.error('Erro ao cadastrar usuário:', error);
            alert('Ocorreu um erro ao cadastrar o usuário.');
        }
    }
}
