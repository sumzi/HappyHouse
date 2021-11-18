$(function () {

    $.get('res/AptDealHistory.xml', dealSearch);

    $('#listBtn').click(function () {
        $.get('res/AptDealHistory.xml', dongSearch);
    })

});

function dealSearch(result) {
    
    var content = ``;

    $('item', result).each(function (index, item) {
        
        
        var aptName = $('아파트', item).text();
        var price =  $('거래금액', item).text();;
        var area =  $('전용면적', item).text();;
        var date =  $('년', item).text() +"."+ $('월', item).text()+"." + $('일', item).text();

        if ($('#inputGroupSelect01').val() == "all" && $('#inputGroupSelect02').val() == "all" && $('#inputGroupSelect03').val() == "all") {
            content += `
            <div style="font-weight: bold;">
                아파트명: ${aptName}<br>
                거래금액: ${price}<br>
                전용면적: ${area}<br>
                거래날짜: ${date}<br><br>
            </div>
            `;
        }

    })
    $('#dealinfo').html(content);


}


function dongSearch(result) {
    
    var content = ``;
    var areaDong = ["필운동", "사직동", "내수동", "익선동", "인의동", "이화동", "충신동", "동숭동", "명륜1가",
        "명륜2가", "창신동", "숭인동", "평동", "홍파동", "교북동", "평창동", "무악동"];

    // var selDong = $(this).text();
    var selDong = $('#inputGroupSelect03').val();
    $('item', result).each(function (index, item) {
        
        var dong = $('법정동', item).text().replace(/ /gi, '');
        var aptName = $('아파트', item).text();
        var price =  $('거래금액', item).text();
        var area =  $('전용면적', item).text();
        var date =  $('년', item).text() +"."+ $('월', item).text()+"." + $('일', item).text();

        if (areaDong[selDong - 1] === dong) {
            console.log(dong);
            content += `
            <div style="font-weight: bold;">
                아파트명: ${aptName}<br>
                거래금액: ${price}<br>
                전용면적: ${area}<br>
                거래날짜: ${date}<br><br>
            </div>
            `;
        }
            
    })
    $('#dealinfo').html(content);

}