/*
 * ============================================================================
 *  Copyright © 2002-2020 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.value;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.tquadrat.foundation.value.Length.CENTIMETER;
import static org.tquadrat.foundation.value.Length.KILOMETER;
import static org.tquadrat.foundation.value.Length.METER;
import static org.tquadrat.foundation.value.Length.PICA;

import java.math.BigDecimal;

import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.PlaygroundClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;

/**
 *  Some playing around with
 *  {@link LengthValue}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: LengthTester.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@PlaygroundClass
@ClassVersion( sourceVersion = "$Id: LengthTester.java 820 2020-12-29 20:34:22Z tquadrat $" )
public final class LengthTester
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class!
     */
    private LengthTester() { throw new PrivateConstructorForStaticClassCalledError( LengthTester.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  The program entry point.
     *
     *  @param  args    The command line arguments.
     */
    public static void main( final String... args )
    {
        try
        {
            final var v0 = new BigDecimal( "3.1415" );
            out.println( v0 );
            out.printf( "%f%n", v0 );

            out.println( BigDecimal.ONE );
            out.printf( "%f%n", BigDecimal.ONE );

            final var v1 = new LengthValue( METER, BigDecimal.ONE );
            out.println( v1 );
            out.printf( "%s%n", v1 );
            out.printf( "%1s%n", v1 );
            out.printf( "%1.1s%n", v1 );
            out.printf( "%-20s%n", v1 );
            out.printf( "%20s%n", v1 );

            v1.setUnit( CENTIMETER );
            out.println( v1 );
            out.printf( "%s%n", v1 );
            out.printf( "%1s%n", v1 );
            out.printf( "%1.1s%n", v1 );
            out.printf( "%-20s%n", v1 );
            out.printf( "%20s%n", v1 );

            v1.setUnit( PICA );
            out.println( v1 );
            out.printf( "%s%n", v1 );
            out.printf( "%1s%n", v1 );
            out.printf( "%1.1s%n", v1 );
            out.printf( "%-20s%n", v1 );
            out.printf( "%20s%n", v1 );

            final var v2 = v1.copy();
            out.println( v1.equals( v2 ) );
            v2.setUnit( KILOMETER );
            out.println( v1.equals( v2 ) );

            out.printf( "%.0s%n", v2 );

            final var v3 = new LengthValue( METER, 555 );
            v3.setUnit( KILOMETER );
            out.printf( "%.0s%n", v3 );
        }
        catch( final Throwable t )
        {
            //---* Handle any previously unhandled exceptions *----------------
            t.printStackTrace( err );
        }
    }   //  main()

}
//  class LengthTester

/*
 *  End of File
 */