/**
 * @param {Date} inputDate 
 * @param {String} format =>"YYYY-MM-DD"、"YYYY-MM-DD HH:mm:ss"、"HH:mm:ss" 支持'/'分隔符
 * @param {Boolean} day 
 */
import moment from 'moment'
function formatDateTime(inputDate, format = "YYYY-MM-DD", day = false) {
    if (inputDate !== null && inputDate !== "") {
        if (day == true) {
            return moment(inputDate).format(`${format}-dddd`);
        }
        return moment(inputDate).format(format);
    }
}

function getMonthDaysCount(year, month) {
    let date = new Date(year, month, 0);
    return date.getDate();
}

export default { formatDateTime, getMonthDaysCount }
