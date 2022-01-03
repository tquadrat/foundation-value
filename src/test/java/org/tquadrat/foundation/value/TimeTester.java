/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.value;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.err;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static org.tquadrat.foundation.value.Time.HOUR;
import static org.tquadrat.foundation.value.Time.MILLISECOND;
import static org.tquadrat.foundation.value.Time.MINUTE;
import static org.tquadrat.foundation.value.Time.SECOND;
import static org.tquadrat.foundation.value.Time.WEEK;

import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.PlaygroundClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;

/**
 *  Some playing around with
 *  {@link TimeValue}.
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TimeTester.java 827 2021-01-04 17:01:34Z tquadrat $
 */
@PlaygroundClass
@ClassVersion( sourceVersion = "$Id: TimeTester.java 827 2021-01-04 17:01:34Z tquadrat $" )
public final class TimeTester
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class!
     */
    private TimeTester() { throw new PrivateConstructorForStaticClassCalledError( TimeTester.class ); }

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
            final var v1 = new TimeValue( SECOND, 1 );
            out.println( v1 );
            out.printf( "%s%n", v1 );
            out.printf( "%1s%n", v1 );
            out.printf( "%1.1s%n", v1 );
            out.printf( "%-20s%n", v1 );
            out.printf( "%20s%n", v1 );

            v1.setUnit( WEEK );
            out.println( v1 );
            out.printf( "%s%n", v1 );
            out.printf( "%1s%n", v1 );
            out.printf( "%1.1s%n", v1 );
            out.printf( "%-20s%n", v1 );
            out.printf( "%20s%n", v1 );

            final var v2 = v1.copy();
            out.println( v1.equals( v2 ) );
            v2.setUnit( HOUR );
            out.println( v1.equals( v2 ) );

            out.printf( "%.0s%n", v2 );

            final var v3 = new TimeValue( MINUTE, 35 );
            v3.setUnit( HOUR );
            out.printf( "%.0s%n", v3 );

            final var v4 = new TimeValue( MILLISECOND, currentTimeMillis() );
            out.printf( "%30s%n", v4 );
            out.printf( "%#30s%n", v4 );
            out.printf( "%-30s%n", v4 );
            out.printf( "%-#30s%n", v4 );

            for( final var t : Time.values() )
            {
                out.printf( "%s: %f%n", t.name(), t.factor() );
            }

            for( var i = 0; i < 180; ++i )
            {
                out.printf( "%#s%n", new TimeValue( MILLISECOND, currentTimeMillis() ) );
                sleep( 1000 );
            }
        }
        catch( final Throwable t )
        {
            //---* Handle any previously unhandled exceptions *----------------
            t.printStackTrace( err );
        }
    }   //  main()

}
//  class TimeTester

/*
 *  End of File
 */