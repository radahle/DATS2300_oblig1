package oblig1;

import java.util.*;

/**
 *
 * @author RudiAndre
 */
public class Oblig1 {

    /**
     * Oppgave 1
     */
    //Mangler teorispørsnålene
    public static int maks(int[] a) {

        if (a.length == 0) {
            throw new NoSuchElementException("Tabellen a er tom");
        }

        int maksverdi = a[0];

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                bytt(a, i, i + 1);

            }
            maksverdi = a[i + 1];
        }
        return maksverdi;
    }

    public static int ombyttinger(int[] a) {
        if (a.length == 0) {
            throw new NoSuchElementException("Tabellen a er tom");
        }

        int ombyttinger = 0;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                bytt(a, i, i + 1);
                ombyttinger++;
            }
        }
        return ombyttinger;
    }

    /**
     * Oppgave 2
     */
    public static int antallUlikeSortert(int[] a) {
        if (a.length == 0) {
            return 0;
        }

        int antall = 1;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                throw new IllegalStateException(
                        "Tabellen er ikke sortert stigende!");
            }
            if (a[i] != a[i + 1]) {
                antall++;
            }
        }
        return antall;
    }

    /**
     * Oppgave 3
     */
    public static int antallUlikeUsortert(int[] a) {
        int lengde = a.length;
        if (lengde < 1) {
            return 0;
        }

        int antall = lengde;

        for (int i = 0; i < lengde; i++) {
            for (int j = i + 1; j < lengde; j++) {
                if (a[j] == a[i]) {
                    antall--;
                    break;
                }
            }
        }
        return antall;
    }

    /**
     * Oppgave 4
     */
    public static void delsortering(int[] a) {
        int oddPos = 0; 
        int lengde = a.length;
        
        for (; oddPos < lengde && a[oddPos] % 2 != 0; oddPos++) {
        } 
        for (int parPos = oddPos + 1; parPos < lengde; parPos++) {
            if (a[parPos] % 2 != 0) {
                int temp = a[oddPos];
                a[oddPos] = a[parPos];
                a[parPos] = temp;
                oddPos++;
            }
        }
        kvikksortering(a, 0, oddPos);
        kvikksortering(a, oddPos, a.length);
    }

    /**
     * Oppgave 5
     */
    public static void rotasjon(char[] a) {
        int n = a.length - 1;

        for (int i = 0; i < n; i++) {
            bytt(a, i, n);
        }
    }

    /**
     * Oppgave 6
     */
    public static void rotasjon(char[] a, int k) {
        int n = a.length;
        if (n < 2) {
            return;                                     // tomt eller en verdi
        }
        if ((k %= n) < 0) {
            k += n;                              // motsatt vei?
        }
        for (int v = 0, h = n - 1; v < h; bytt(a, v++, h--));  // snur a[a:n>
        for (int v = 0, h = k - 1; v < h; bytt(a, v++, h--));  // snur a[0:d>
        for (int v = k, h = n - 1; v < h; bytt(a, v++, h--));  // snur a[d:n>
    }

    /**
     * Oppgave 7 a
     */
    public static String flett(String s, String t) {
        int kortest = Math.min(s.length(), t.length());

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < kortest; i++) {
            sbuilder.append(s.charAt(i)).append(t.charAt(i));
        }

        sbuilder.append(s.substring(kortest)).append(t.substring(kortest));

        return sbuilder.toString();
    }

    /**
     * Oppgave 7 b
     */
    public static String flett(String... s) {
        int lengde = s.length;

        if (lengde < 1) {
            return "";
        }

        int lengst = s[0].length();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 1; i < lengde; i++) {
            if (lengst < s[i].length()) {
                lengst = s[i].length();
            }
        }

        for (int i = 0; i < lengst; i++) {
            for (int j = 0; j < lengde; j++) {
                if (i < s[j].length()) {
                    sbuilder.append(s[j].charAt(i));
                }
            }
        }
        return sbuilder.toString();
    }

    /**
     * Oppgave 8
     */
    public static int[] indekssortering(int[] a) {
        int[] indeks = new int[a.length];
        int[] temp = a.clone();

        int lengde = a.length;

        kvikksortering(temp);

        for (int i = 0; i < lengde; i++) {
            for (int j = 0; j < lengde; j++) {
                if (temp[i] == a[j]) {
                    indeks[i] = j;
                }
            }
        }
        return indeks;
    }

    /**
     * Oppgave 9
     */
    public static int[] tredjeMin(int[] a) {
        int lengde = a.length;
        if (lengde < 3) {
            throw new NoSuchElementException("Tabellen er for kort!");
        }

        int m = 0;          //posisjonen til minste verdi
        int nm = 1;         //posisjonen til nest minst verdi
        int tm = 2;         //posisjonen til tredje minst verdi

        if (a[2] < a[1]) {
            if (a[2] < a[0]) {
                m = 2;
                tm = 0;
            } else {
                nm = 2;
                tm = 1;
            }
        }

        if (a[1] < a[0]) {
            m = 1;
            nm = 0;
        }

        int minstverdi = a[m];
        int nestminst = a[nm];
        int tredjeminst = a[tm];

        for (int i = 3; i < lengde; i++) {
            if (a[i] < tredjeminst) {
                if (a[i] < nestminst) {
                    if (a[i] < minstverdi) {
                        tm = nm;
                        tredjeminst = nestminst;

                        nm = m;
                        nestminst = minstverdi;

                        m = i;
                        minstverdi = a[m];
                    } else {
                        tm = nm;
                        tredjeminst = nestminst;

                        nm = i;
                        nestminst = a[nm];
                    }
                } else {
                    tm = i;
                    tredjeminst = a[tm];
                }
            }
        }
        return new int[]{m, nm, tm};
    }

    /**
     * Oppgave 10
     */
    public static boolean inneholdt(String a, String b) {
        int[] k = new int[250];
        int[] j = new int[250];

        for (char c : a.toCharArray()) {
            k[c]++;
        }

        for (char c : b.toCharArray()) {
            j[c]++;
        }

        for (int i = 0; i < k.length; i++) {
            if (k[i] > j[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] tabell = new int[]{2, 3, 1, 4, 5, 6};
        int[] b = Oblig1Test.randPerm(10);
        //System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(tredjeMin(tabell)));

    }

    //Hjelpemetoder
    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void bytt(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static int parter(int[] a, int v, int h, int skilleverdi) {
        while (v <= h && a[v] < skilleverdi) {
            v++;     // h er stoppverdi for v
        }
        while (v <= h && a[h] >= skilleverdi) {
            h--;    // v er stoppverdi for h
        }
        while (true) // stopper når v >= h
        {
            if (v < h) {
                bytt(a, v++, h--);          // bytter om a[v] og a[h]
            } else {
                return v;                             // partisjoneringen er ferdig
            }
            while (a[v] < skilleverdi) {
                v++;             // flytter v mot høyre
            }
            while (a[h] >= skilleverdi) {
                h--;            // flytter h mot venstre
            }
        }
    }

    public static int sParter(int[] a, int v, int h, int indeks) {
        bytt(a, indeks, h);   // skilleverdi a[indeks] flyttes bakerst
        int k = parter(a, v, h - 1, a[h]);  // partisjonerer a[v:h − 1]
        bytt(a, k, h);       // bytter for å få skilleverdien på rett plass
        return k;                   // returnerer posisjonen til skilleverdien
    }

    private static void kvikksortering1(int[] a, int v, int h) // en privat metode
    {
        if (v >= h) {
            return;  // a[v:h] er tomt eller har maks ett element
        }
        int k = sParter(a, v, h, (v + h) / 2);  // bruker midtverdien
        kvikksortering1(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering1(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    public static void kvikksortering(int[] a, int fra, int til) // fra/til i sortering
    {
        kvikksortering1(a, fra, til - 1);  // v = fra, h = til - 1
    }

    public static void kvikksortering(int[] a) // sorterer hele tabellen
    {
        kvikksortering1(a, 0, a.length - 1);
    }
}
