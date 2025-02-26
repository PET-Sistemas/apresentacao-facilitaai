/* eslint-disable */
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const API_URL = 'http://localhost:8080'; // Substitua pelo URL do seu back-end

const login = async (email, password) => {
    try {
        const response = await axios.post(`${API_URL}/api/auth/login`, { email: email, senha: password });
        console.table(response);
        return response.data;
    } catch (error) {
        console.error('Erro ao fazer login:', error);
        console.log(email, password)
        throw error;
    }
};

export default function useLoginViewModel() {
    const email = ref('');
    const password = ref('');
    const router = useRouter();

    const handleLogin = async () => {
        try {
            const result = await login(email.value, password.value);
            alert(result);
            sessionStorage.setItem("emailUsuario", email.value);
            router.push('/inicial_tela'); 
        } catch (error) {
            router.push('/inicial_tela');
            //console.error('Falha no login:', error);
        }
    };

    return {
        email,
        password,
        handleLogin
    };
}
