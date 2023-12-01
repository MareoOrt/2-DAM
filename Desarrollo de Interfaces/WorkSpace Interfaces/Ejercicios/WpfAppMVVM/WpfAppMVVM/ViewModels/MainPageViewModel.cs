using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using WpfAppMVVM.Repositorio;
using WpfAppMVVM.Models;

namespace WpfAppMVVM.ViewModels
{
    internal class MainPageViewModel : BaseViewModel
    {
        private Persona currentPersona;
        private int indice;
        public ObservableCollection<Persona> Personas
        { get; private set; } = new();
        public String Posicion { get => $"{indice + 1}/{Personas.Count}"; }

        #region Declaración de comandos
        public ICommand SiguienteCommand { get; private set; }

        public ICommand AnteriorCommand { get; private set; }

        public ICommand PrimeroCommand { get; private set; }

        public ICommand UltimoCommand { get; private set; }

        public ICommand CargarCommand { get; private set; }
        #endregion

        public MainPageViewModel()
        {
            CargarCommand = new RelayCommand(Cargar);

            PrimeroCommand = new RelayCommand(() =>
            { CurrentPersona = Personas.Last(); Indice = 0; },
            () => Personas.Count > 0);

            UltimoCommand = new RelayCommand(() =>
            { CurrentPersona = Personas.Last(); Indice = Personas.Count - 1; },
            () => Personas.Count > 0);

            AnteriorCommand = new RelayCommand(() =>
            CurrentPersona = Personas.ElementAt(--Indice),
            () => Indice > 0);

            SiguienteCommand = new RelayCommand(() =>
            CurrentPersona = Personas.ElementAt(++Indice),
            () => Indice < Personas.Count - 1);

        }

        public Persona CurrentPersona
        {
            get => currentPersona;
            set
            {
                if (currentPersona != value)
                {
                    currentPersona = value;
                    OnPropertyChange();
                }
            }
        }

        public int Indice
        {
            get => indice;
            set
            {
                indice = value;
                OnPropertyChange("Posicion");
            }
        }

        private void Cargar()
        {
            Personas = Repositorio.Datos.GetPersonas();
            Indice = 0;
            CurrentPersona = Personas.First();

        }

        public class RelayCommand : ICommand
        {
            private Action _execute;
            private Func<bool> _canExecute;

            public RelayCommand(Action execute, Func<bool> canExecute = null)
            {
                _execute = execute;
                _canExecute = canExecute;
            }

            public bool CanExecute(object? parameter)
            {
                return _canExecute == null || _canExecute();
            }

            public void Execute(object? parameter)
            {
                _execute();
            }

            public event EventHandler? CanExecuteChanged
            {
                add { CommandManager.RequerySuggested += value; }
                remove { CommandManager.RequerySuggested -= value; }
            }
        }


    }
}
