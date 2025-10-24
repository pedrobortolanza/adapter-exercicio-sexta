public class InstagramAdapterFactory implements FactoryMidiaSocial {
    @Override
    public AdapterRedes criarAdaptador() {
        return new InstagramAdapter();
    }
    
}
