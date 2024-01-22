using CommunityToolkit.Maui.Core.Views;
using CommunityToolkit.Maui.Views;
using System.Windows.Input;

namespace MauiAppToolKit;

public partial class SimplePopup : Popup
{
    public ICommand TapGestureRecognizer_Tapped { get; private set; }
    public ICommand Button_Clicked { get; private set; }
    public SimplePopup()
    {
        InitializeComponent();

        TapGestureRecognizer_Tapped = new Command<object>((o) =>
        {
            ((SimplePopup)o).Close();
        });
        Button_Clicked = new Command<object>((o) => { ((SimplePopup)o).Close(); });
        this.BindingContext = this;
    }

}