
using EjMA01.Pages;
using System.ComponentModel;
using System.Windows.Input;

namespace EjMA01.ViewModel
{
    public class MainPageVM : INotifyPropertyChanged
    {
        public int contador = 0;
        public int _contador = 0;

        public event PropertyChangedEventHandler? PropertyChanged;

        public int Contador
        {
            get { return contador; }
            set {
                contador = value;
                PropertyChanged?.Invoke
                    (this, new PropertyChangedEventArgs(nameof(Contador)));
            }
        }

        public ICommand SumarCommand{ get; private set; }
        public ICommand SumarCommand2 { get; private set; }
        public ICommand SiguienteCommand { get; private set; }

        public MainPageVM()
        {
            SumarCommand = new Command<Button>((boton) =>
            {
                contador++;
                boton.Text = contador.ToString();
            });

            SiguienteCommand = new Command(async async => {
                await Shell.Current.GoToAsync
                    ($"{nameof(NewPage1)}");
            });
        }
    }
}
