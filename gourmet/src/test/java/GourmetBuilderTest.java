import com.jugarte.gourmet.beans.Gourmet;
import com.jugarte.gourmet.builders.GourmetInternalBuilder;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by javiergon on 30/07/15.
 */
public class GourmetBuilderTest extends BaseTest {

    @Test
    public void testResponseOk() throws Exception {
        String data = this.utils.getResourceToString("response_ok.html");

        GourmetInternalBuilder gourmetBuilder = new GourmetInternalBuilder();
        gourmetBuilder.append(GourmetInternalBuilder.DATA_JSON, data);
        Gourmet gourmet = (Gourmet) gourmetBuilder.build();

        assertEquals(gourmet.errorCode, "0");
        assertEquals(gourmet.errorMessage, "");

        assertEquals(gourmet.currentBalance, "34,56");
        assertNotNull(gourmet.operations);
        assertTrue(gourmet.operations.size() == 9);
        assertEquals(gourmet.operations.get(0).name, "BURGER KING QUEVEDO");
        assertEquals(gourmet.operations.get(0).price, "7,45");
        assertEquals(gourmet.operations.get(0).date, "05/07/2015");
        assertEquals(gourmet.operations.get(0).hour, "22:58");

        assertEquals(gourmet.operations.get(1).name, "COECOE HOSTELEROS");

        assertEquals(gourmet.operations.get(3).name, "DI BOCCA RESTAURACIO");
        assertEquals(gourmet.operations.get(3).price, "11,95");

        assertEquals(gourmet.operations.get(8).name, "BURGER KING QUEVEDO");
        assertEquals(gourmet.operations.get(8).price, "1,49");
        assertEquals(gourmet.operations.get(8).date, "21/06/2015");
        assertEquals(gourmet.operations.get(8).hour, "21:55");
    }

    @Test
    public void testResponseWithoutOperations() throws Exception {
        String data = this.utils.getResourceToString("response_okwithoutops.html");

        GourmetInternalBuilder gourmetBuilder = new GourmetInternalBuilder();
        gourmetBuilder.append(GourmetInternalBuilder.DATA_JSON, data);
        Gourmet gourmet = (Gourmet) gourmetBuilder.build();

        assertEquals(gourmet.errorCode, "0");
        assertEquals(gourmet.errorMessage, "");

        assertNotNull(gourmet.currentBalance);
        assertNull(gourmet.operations);
    }

    @Test
    public void testResponseFail() throws Exception {
        String data = this.utils.getResourceToString("response_fail.html");

        GourmetInternalBuilder gourmetBuilder = new GourmetInternalBuilder();
        gourmetBuilder.append(GourmetInternalBuilder.DATA_JSON, data);
        Gourmet gourmet = (Gourmet) gourmetBuilder.build();

        assertEquals(gourmet.errorCode, "2");
        assertEquals(gourmet.errorMessage, "Usuario o contraseña incorrectos");

        assertNull(gourmet.currentBalance);
        assertNull(gourmet.operations);
    }

    @Test
    public void testResponseEmptyAndNull() throws Exception {
        String data = "";
        GourmetInternalBuilder gourmetBuilder = new GourmetInternalBuilder();
        gourmetBuilder.append(GourmetInternalBuilder.DATA_JSON, data);
        Gourmet gourmet = (Gourmet) gourmetBuilder.build();

        assertEquals(gourmet.errorCode, "3");
        assertEquals(gourmet.errorMessage, "El servidor no responde");
        assertNull(gourmet.currentBalance);
        assertNull(gourmet.operations);

        data = null;
        gourmetBuilder = new GourmetInternalBuilder();
        gourmetBuilder.append(GourmetInternalBuilder.DATA_JSON, data);
        gourmet = (Gourmet) gourmetBuilder.build();

        assertEquals(gourmet.errorCode, "3");
        assertEquals(gourmet.errorMessage, "El servidor no responde");
        assertNull(gourmet.currentBalance);
        assertNull(gourmet.operations);

        data = "ldkjfalkdjfoasdjfalkdjfalñkdjfañldkjfalñkdjf";
        gourmetBuilder = new GourmetInternalBuilder();
        gourmetBuilder.append(GourmetInternalBuilder.DATA_JSON, data);
        gourmet = (Gourmet) gourmetBuilder.build();

        assertEquals(gourmet.errorCode, "3");
        assertEquals(gourmet.errorMessage, "El servidor no responde");
        assertNull(gourmet.currentBalance);
        assertNull(gourmet.operations);
    }

    @Test
    public void testHelperClass() throws Exception {

        GourmetInternalBuilder gourmetBuilder = new GourmetInternalBuilder();

        assertEquals(gourmetBuilder.cleanString(" hola "), "hola");
        assertEquals(gourmetBuilder.cleanString(" hola    "), "hola");
        assertEquals(gourmetBuilder.cleanString(" hola  \n  \t  "), "hola");
        assertEquals(gourmetBuilder.cleanString(" hola paco  \n  \t  "), "hola paco");

        assertEquals(gourmetBuilder.removeLastWord("Hola"), "Hol");
        assertEquals(gourmetBuilder.removeLastWord("Restaurante M"), "Restaurante");
        assertEquals(gourmetBuilder.removeLastWord(" Restaurante  M"), "Restaurante");

    }

}
