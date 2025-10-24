public class TwitterAdapterFactory implements FactoryMidiaSocial {
    @Override
    public AdapterRedes criarAdaptador() {
        return new TwitterAdapter();
    }
    
}
