using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MauiAppRestaurantes
{
    internal class MainPageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler? PropertyChanged;

        private ObservableCollection<Restaurante> restaurantes;
        public Restaurante currentRestaurante { get; set; }
        public String Posicion { get; set; }

        public Command SiguienteCommand { get; private set; }
        public Command AnteriorCommand { get; private set; }

        private int indice;
        public int Indice
        {
            get { return indice; }
            set
            {
                if (value >= 0 && value <= restaurantes.Count - 1)
                {
                    indice = value;
                    currentRestaurante = restaurantes[indice];
                    Posicion = $"{indice + 1}/{restaurantes.Count}";
                    PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(nameof(currentRestaurante)));
                    PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(nameof(Posicion)));
                    AnteriorCommand?.ChangeCanExecute();
                    SiguienteCommand?.ChangeCanExecute();
                }
            }
        }
        public MainPageViewModel()
        {
            restaurantes = new ObservableCollection<Restaurante>
            {
                new Restaurante{Titulo="UNO",
                    Nombre="restaurante uno",
                    Lema="el número 1",
                    Direccion="Calle del uno,11",
                    Localidad="San Cipriuno",
                    Provincia="Valladolid",
                    Foto="https://picsum.photos/id/10/300/300",
                    ColorBase=Colors.AliceBlue},
                new Restaurante{Titulo="DOS",
                    Nombre="restaurante dos",
                    Lema="el número 2",
                    Direccion="Calle del dos,22",
                    Localidad="Dosaguas",
                    Provincia="Palencia",
                    Foto="https://picsum.photos/id/11/300/300",
                    ColorBase=Colors.AntiqueWhite},
                new Restaurante{Titulo="TRES",
                    Nombre="restaurante tres",
                    Lema="el número 3",
                    Direccion="Calle del tres,33",
                    Localidad="Tres Pinares",
                    Provincia="Valladolid",
                    Foto="https://picsum.photos/id/12/300/300", 
                    ColorBase = Colors.Aqua},
                new Restaurante{Titulo="CUATRO",
                    Nombre="restaurante cuatro",
                    Lema="el número 4",
                    Direccion="Calle del cuatro,44",
                    Localidad="VillaCuatro",
                    Provincia="Salamanca",
                    Foto="https://picsum.photos/id/13/300/300",
                    ColorBase=Colors.Aquamarine}
            };
            Indice = 0;
            SiguienteCommand = new Command(() => { Indice++; }, () => Indice < restaurantes.Count - 1);
            AnteriorCommand = new Command(Retroceder, () => Indice > 0);
        }

        private void Retroceder()
        {
            Indice--;
        }

    }
}
