/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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

import org.tquadrat.foundation.value.AreaValueStringConverter;
import org.tquadrat.foundation.value.DataSizeValueStringConverter;
import org.tquadrat.foundation.value.LengthValueStringConverter;
import org.tquadrat.foundation.value.MassValueStringConverter;
import org.tquadrat.foundation.value.PressureValueStringConverter;
import org.tquadrat.foundation.value.SpeedValueStringConverter;
import org.tquadrat.foundation.value.TemperatureValueStringConverter;
import org.tquadrat.foundation.value.TimeValueStringConverter;
import org.tquadrat.foundation.value.VolumeValueStringConverter;

/**
 *  The module for the definition of value classes for the Foundation Library.
 *
 *  @provides   org.tquadrat.foundation.lang.StringConverter    Implementations
 *      of String converters.
 *
 *  @version $Id: module-info.java 990 2022-01-14 23:34:24Z tquadrat $
 *
 *  @todo task.list
 */
module org.tquadrat.foundation.value
{
    requires java.base;
    requires transitive org.tquadrat.foundation.util;
    requires org.tquadrat.foundation.i18n;

    //---* Common Use *--------------------------------------------------------
    exports org.tquadrat.foundation.value.api;
    exports org.tquadrat.foundation.value;

    provides org.tquadrat.foundation.lang.StringConverter with
        AreaValueStringConverter,
        DataSizeValueStringConverter,
        LengthValueStringConverter,
        MassValueStringConverter,
        PressureValueStringConverter,
        SpeedValueStringConverter,
        TemperatureValueStringConverter,
        TimeValueStringConverter,
        VolumeValueStringConverter;
}

/*
 *  End of File
 */