using EjMA01.ViewModel;

namespace EjMA01
{
    public partial class MainPage : ContentPage
    {

        public MainPage(MainPageVM vm)
        {
            InitializeComponent();
            this.BindingContext = vm;
        }
    }

}
