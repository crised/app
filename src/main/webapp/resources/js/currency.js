(function ($) {
    $.fn.rewrite_number = function (x) {
        console.log(addCommas(this.val()));
        var number = this.val().text($(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
    };

    function addCommas(nStr) {
        nStr += '';
        var x = nStr.split('.');
        var x1 = x[0];
        var x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + '.' + '$2');
        }
        return x1 + x2;
    }
})(jQuery);


jQuery(function ($) {
    $('#addAdForm\\:priceInput').on('blur', function () {
        $(this).rewrite_number();
    });
});