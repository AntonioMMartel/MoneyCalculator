package moneycalculator.view.ui;

import moneycalculator.Models.Money;

/**
 * @author Antonio Miguel Martel
 * Vista del objeto que muestra la moneda a la que queremos cambiar
 */
public interface MoneyComponent {
    public Money get();
    public void updateAmount(double newAmount);
}
