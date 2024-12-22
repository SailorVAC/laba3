import javax.swing.table.AbstractTableModel;
    @SuppressWarnings("serial")
    public class GornerTableModel extends AbstractTableModel {

        private Double[] coefficients;
        private Double from;
        private Double to;
        private Double step;

        public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
            this.from = from;
            this.to = to;
            this.step = step;
            this.coefficients = coefficients;
        }

        public Double getFrom() {
            return from;
        }

        public Double getTo() {
            return to;
        }

        public Double getStep() {
            return step;
        }

        public int getColumnCount() {
// В данной модели два столбца
            return 3;
        }

        public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
            return (int) (Math.ceil((to - from) / step)) + 1;
        }

        public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
            double x = from + step * row;
            if (col == 0) return x;
            if (col == 1) {
                Double result = 0.0;
                for (int i = 0; i < coefficients.length; i++) result = result * x + coefficients[i];
                return result;
            }
            if (col == 2) {
                String valueString = String.valueOf(getValueAt(row, 1));
                char firstChar = valueString.charAt(0); // Первая цифра
                char lastChar = valueString.charAt(valueString.length() - 1); // Последняя цифра
                return firstChar == lastChar; // Проверка совпадения
            }
            return null;
        }

        public String getColumnName(int col) {
            switch (col) {
                case 0:
// Название 1-го столбца
                    return "Значение X";
                case 1:
// Название 2-го столбца
                    return "Значение многочлена";

                case 2:
                    return "краева симметрия";

                default:
                    return null;
            }
        }

        public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
            switch (col) {
                case 0:
                case 1:
                    return Double.class;
                case 2:
                    return Boolean.class;
                default:
                    return Object.class;
            }
        }
    }
