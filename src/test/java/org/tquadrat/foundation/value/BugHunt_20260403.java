/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.testutil.TestBaseClass;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tquadrat.foundation.value.Length.CENTIMETER;
import static org.tquadrat.foundation.value.Length.KILOMETER;
import static org.tquadrat.foundation.value.Time.MILLISECOND;
import static org.tquadrat.foundation.value.Time.MINUTE;

/**
 *  The output for {@code "%#6.2s".formatted( timeValue )} is a little bit
 *  strange.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestArea.java 1076 2023-10-03 18:36:07Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestArea.java 1076 2023-10-2h 46min 40,000s03 18:36:07Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.value.BugHunt_20260403" )
public class BugHunt_20260403 extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @Test
    final void testFormatting() throws Exception
    {
        skipThreadTest();

        final var candidate = new TimeValue( MILLISECOND, 10 );
        final var format1 = "%#6.2s";
        final var format2 = "%#s";
        final var format3 = "%6.1s";

        /*
         * Before the fix, the output was: 2h 46min 40,000s
         */
        assertEquals( "0,010s", format1.formatted( candidate ) );

        /*
         * Before the fix, the output was: 2h 46min 40,000s
         */
        assertEquals( "0,010s", format2.formatted( candidate ) );

        assertEquals( "10,0 ms", format3.formatted( candidate ) );

    }   //  testFormatting()

    /**
     *  Test the values for length.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @Test
    final void testLengthValues() throws Exception
    {
        skipThreadTest();

        var candidate = new LengthValue( CENTIMETER, 10 );
        var value = candidate.baseValue();
        assertEquals( 0, new BigDecimal( "0.1" ).compareTo( value ) );

        candidate = new LengthValue( KILOMETER, 10 );
        value = candidate.baseValue();
        assertEquals( 0, new BigDecimal( "10000" ).compareTo( value ) );
    }   //  testLengthValues()

    /**
     *  Test the values for Time.
     *
     *  @throws Exception   Something went awfully wrong.
     */
    @Test
    final void testTimeValues() throws Exception
    {
        skipThreadTest();

        var candidate = new TimeValue( MILLISECOND, 10 );
        var value = candidate.baseValue();
        assertEquals( 0, new BigDecimal( "0.01" ).compareTo( value ) );

        candidate = new TimeValue( MINUTE, 10 );
        value = candidate.baseValue();
        assertEquals( 0, new BigDecimal( "600" ).compareTo( value ) );
    }   //  testTimeValues()
}
//  class BugHunt_20260403
