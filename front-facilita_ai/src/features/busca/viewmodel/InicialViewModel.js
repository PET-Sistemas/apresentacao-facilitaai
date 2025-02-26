export default {
  data() {
    return {
      address: 'Endereço Atual',
      newAddress: '',
      isEditing: false,
      price: 3000,
      selectedServices: []
    };
  },
  methods: {
    getSliderBackground(value, max) {
      const percentage = (value / max) * 100;
      return {
        '--value': percentage + '%',
      };
    },
    updateSlider(event, type) {
      if (type === 'price') {
        this.price = parseInt(event.target.value);
      }
    },
    formatPrice(price) {
      return price.toLocaleString('pt-BR', { 
        style: 'currency', 
        currency: 'BRL',
        minimumFractionDigits: 2
      });
    },
    toggleEdit() {
      this.isEditing = !this.isEditing;
      if (this.isEditing) {
        this.$nextTick(() => {
          this.$refs.addressInput?.focus();
        });
      }
    },
    saveAddress() {
      if (this.newAddress.trim()) {
        this.address = this.newAddress;
        this.newAddress = '';
        this.isEditing = false;
      }
    },
    toggleSolicitado(service) {
      service.solicitado = !service.solicitado;
    }
  }
};