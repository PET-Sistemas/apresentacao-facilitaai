<template>
  <HeaderPage />
  <div class="main-container">
    <nav class="sidebar">
      <div class="filter-section">
        <div class="filter-container">
          <h3>TIPO DE SERVIÇO</h3>
          <div class="checkbox-container">
            <label>
              <input type="checkbox" value="limpeza" v-model="selectedServices"> Limpeza
            </label>
            <label>
              <input type="checkbox" value="construcao" v-model="selectedServices"> Construção
            </label>
            <label>
              <input type="checkbox" value="manutencao" v-model="selectedServices"> Manutenção
            </label>
            <label>
              <input type="checkbox" value="eletrica" v-model="selectedServices"> Elétrica
            </label>
            <label>
              <input type="checkbox" value="hidraulica" v-model="selectedServices"> Hidráulica
            </label>
            <label>
              <input type="checkbox" value="pintura" v-model="selectedServices"> Estética
            </label>
          </div>
        </div>
        <div class="filter-container">
          <h3>PREÇO: {{ formatPrice(price) }}</h3>
          <input
            type="range"
            v-model="price"
            min="0"
            max="3000"
            step="50"
            @input="updateSlider($event, 'price')"
            :style="getSliderBackground(price, 3000)"
          />
        </div>
      </div>
    </nav>
    
    <div class="main-content">
      <section class="service-cards">
        <div v-for="service in filteredServices" :key="service.id" class="card">
          <img :src="service.img" alt="Imagem do Serviço" class="service-image" />
          <div class="card-text">
            <h3>{{ service.nome }}</h3>
            <p class="provider-name">{{ service.usuario.nomeCompleto }}</p>
            <p class="rating">
              <span v-for="n in 5" :key="n" class="star" 
                :class="{ filled: n <= service.avaliacao }">★</span>
            </p>
            <p class="description">{{ service.descricao }}</p>
            <div class="price-distance">
              <p>{{ service.usuario.cidade }}, {{ service.usuario.uf }}</p>
              <span class="dot">•</span>
              <p>{{ formatPrice(service.valor) }}</p>
            </div>
            <div class="card-footer">
              <button @click="toggleSolicitado(service)" 
                :class="{'solicitado-button': service.solicitado, 'contratar-button': !service.solicitado}">
                {{ service.solicitado ? 'Solicitado' : 'Contratar' }}
              </button>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import InicialViewModel from '@/features/busca/viewmodel/InicialViewModel';
import InicialModel from '@/features/busca/model/InicialModel';
import HeaderPage from '@/components/header/HeaderPage.vue';

export default {
  name: 'InicialTela',
  mixins: [InicialViewModel, InicialModel],
  components: {
    HeaderPage
  },
  computed: {
    filteredServices() {
      return this.filterServices(this.selectedServices, this.price);
    }
  },
  mounted() {
    this.fetchServices();
  }
};
</script>

<style scoped>
.main-container {
  display: flex;
  height: calc(100vh - 7rem);
  overflow: hidden;
}

.sidebar {
  width: 250px;
  padding: 20px;
  background-color: #f5f5f5;
  overflow-y: auto;
}

.filter-section {
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.filter-container {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.checkbox-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.main-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.service-cards {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.card {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  gap: 20px;
}

.service-image {
  width: 200px;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
}

.card-text {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.provider-name {
  color: #5C5B5B;
  font-family: 'Crete Round', serif;
  font-size: 18px;
}

.description {
  color: #666;
  line-height: 1.4;
}

.price-distance {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #024A59;
  font-weight: 500;
}

.star {
  color: #ccc;
  font-size: 1.2rem;
}

.star.filled {
  color: #FFD700;
}

.contratar-button, .solicitado-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.2s;
}

.contratar-button {
  background-color: #024A59;
  color: white;
}

.contratar-button:hover {
  background-color: #067057;
}

.solicitado-button {
  background-color: #F26530;
  color: white;
}

.solicitado-button:hover {
  background-color: #D9542B;
}

@media (max-width: 768px) {
  .main-container {
    flex-direction: column;
    height: auto;
  }

  .sidebar {
    width: 100%;
    padding: 10px;
  }

  .card {
    flex-direction: column;
  }

  .service-image {
    width: 100%;
    height: 200px;
  }
}
</style>