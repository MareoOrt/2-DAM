
using CommunityToolkit.Maui.Alerts;
using CommunityToolkit.Maui.Core;
using CommunityToolkit.Maui.Views;
using System.Windows.Input;
using Font = Microsoft.Maui.Font;


namespace MauiAppToolKit;


public partial class MainPage : ContentPage
{
    public ICommand ComandoEtiqueta { get; }
    public ICommand ComandoBoton { get; }
    public ICommand ComandoPopup { get; }

    //static Page Page => Application.Current?.MainPage ?? throw new NullReferenceException();
    public MainPage()
    {
        InitializeComponent();

        //Configurar el mensaje de tipo Toast
        string text = "This is a Toast";
        ToastDuration duration = ToastDuration.Short;
        double fontSize = 14;
        var toast = Toast.Make(text, duration, fontSize);

        ComandoEtiqueta = new Command(() => MostrarMensaje());
        ComandoBoton = new Command(async () => await toast.Show());
        ComandoPopup = new Command(() =>
        {
            Shell.Current.ShowPopup(new SimplePopup());
            //SimplePopup popup = new SimplePopup();
            //Page.ShowPopup(popup); //asegurar que en SimplePopup.xaml.cs -> SimplePopup:Popup
        });

        this.BindingContext = this; //establecer como contexto la propia MainPage (sin necesidad de ViewModel...)

    }

    private async void MostrarMensaje()
    {
        var snackbarOptions = new SnackbarOptions
        {
            BackgroundColor = Colors.Red,
            TextColor = Colors.Green,
            ActionButtonTextColor = Colors.Yellow,
            CornerRadius = new CornerRadius(10),
            Font = Font.SystemFontOfSize(14),
            ActionButtonFont = Font.SystemFontOfSize(14),
            CharacterSpacing = 0.5
        };

        //Configurar el mensaje emergente (tipo Snackbar)
        string text = "This is a Snackbar";
        string actionButtonText = "Click Here to Dismiss";
        Action action = async () => await DisplayAlert("Snackbar ActionButton Tapped",
            "The user has tapped the Snackbar ActionButton", "OK");
        TimeSpan duration = TimeSpan.FromSeconds(3);

        var snackbar = Snackbar.Make(Title = text, action, actionButtonText, duration, snackbarOptions);

        await snackbar.Show();
    }

}


