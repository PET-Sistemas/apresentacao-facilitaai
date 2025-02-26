import CadastroservicoModel from '../model/CadastroservicoModel';
import axios from 'axios';

const arrImgs = ['https://mecanie.com.br/artigos/wp-content/uploads/2024/03/tecnico-em-mecanica.png', 'https://www.desentupidorasuprematec.com.br/wp-content/uploads/2016/10/suprematec-desentupidora-servico-encanador-profissional-1.jpg', 'https://www.cnnbrasil.com.br/wp-content/uploads/sites/12/2023/04/super-mario-bros.jpg?w=1200&h=900&crop=1', 'https://static.wixstatic.com/media/28d39c_39fee459703c4ddcb33b511055322794~mv2.jpg/v1/fill/w_1000,h_750,al_c,q_85,usm_0.66_1.00_0.01/28d39c_39fee459703c4ddcb33b511055322794~mv2.jpg'];

export default class CadastroservicoViewModel {
  constructor() {
    this.name = '';
    this.description = '';
    this.price = '';
  }

  uploadPicture(file) {
    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
    }
  }

  async submitForm() {
      try {
          const serviceData = new CadastroservicoModel(
            this.name,
            this.description,
            this.price
          );
          const randomAvaliacao = () => Math.floor(Math.random() * 5) + 1;
          const randomImg = ()=> Math.floor(Math.random()*4);
          const randomPreco = ()=> Math.floor(Math.random()*500)+200;

          const dataReq = '2004-12-10';

         

          console.log('Dados enviados:', {
            name: this.name,
            description: this.description,
            price: this.price // Verifique se está presente e correto
        });
          console.log(serviceData.price);
          // Substitua a URL abaixo pela URL do backend
          const response = await axios.post('http://localhost:8080/api/servicos', {
            usuario: { email: sessionStorage.getItem('emailUsuario') },
            nome: serviceData.name,
            descricao: serviceData.description,
            img: arrImgs[randomImg()],
            data: dataReq, // Certifique-se de que dataReq está sendo incluído
            avaliacao: randomAvaliacao(),
            valor: randomPreco(),
        });

        
          if (response.status === 200) {
              alert('Cadastro de serviço realizado com sucesso!');
          }
      } catch (error) {
          console.error('Erro ao cadastrar serviço:', error);
          alert('Ocorreu um erro ao cadastrar o serviço.');
      }
  }
}