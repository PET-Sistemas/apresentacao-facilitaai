export default {
  data() {
    return {
      services: [],
    };
  },
  methods: {
    async fetchServices() {
      try {
        const response = await fetch('http://localhost:8080/api/servicos');
        const data = await response.json();
        console.log(data);
        this.services = data.map(service => ({ ...service, solicitado: false }));
      } catch (error) {
        console.error('Error fetching services:', error);
      }
    },
    filterServices(selectedServices, price) {
      return this.services.filter(service => {
        const matchesServiceType = selectedServices.length === 0 || 
          selectedServices.some(type => service.nome.toLowerCase().includes(type));
        const matchesPrice = service.valor <= price;
        return matchesServiceType && matchesPrice;
      });
    }
  }
};