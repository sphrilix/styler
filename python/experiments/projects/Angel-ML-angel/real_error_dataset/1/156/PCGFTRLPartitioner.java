package com.tencent.angel.spark.ml.psf.ftrl;

import com.tencent.angel.ml.matrix.MatrixContext;
import com.tencent.angel.ml.matrix.PartitionMeta;
import com.tencent.angel.ps.storage.partitioner.ColumnRangePartitioner;
import com.tencent.angel.ps.storage.partitioner.RangePartitioner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class PCGFTRLPartitioner extends ColumnRangePartitioner {

  private static final Log LOG = LogFactory.getLog(RangePartitioner.class);
  long start, end;

  private static final long[] starts = {0L,193514046488576L,281474976710656L,545357767376896L,897201488265216L,1073123348709376L,1143492092887040L,1671257674219520L,2093470139285504L,2146246697418752L,2216615441596416L,2286984185774080L,3166593487994880L,3360107534483456L,3940649673949184L,4169348092526592L,4274901208793088L,4292493394837504L,4327677766926336L,4450823069237248L,4503599627370496L,6069304185323520L,6597069766656000L,6632254138744832L,6667438510833664L,7142427534032896L,7230388464254976L,7793338417676288L,8268327440875520L,8321103999008768L,8356288371097600L,8532210231541760L,8549802417586176L,8567394603630592L,8584986789675008L,8620171161763840L,8813685208252416L,8866461766385664L,8901646138474496L,8936830510563328L,9024791440785408L,11646027161403392L,12402491161313280L,13088586417045504L,13246916091445248L,13282100463534080L,13317284835622912L,13352469207711744L,13581167626289152L,14460776928509952L,14601514416865280L,14882989393575936L,15410754974908416L,15674637765574656L,16378325207351296L,16554247067795456L,16624615811973120L,16659800184061952L,16694984556150784L,16730168928239616L,17029236090994688L,17046828277039104L,17064420463083520L,17082012649127936L,17099604835172352L,17117197021216768L,17134789207261184L,17152381393305600L,17169973579350016L,17205157951438848L,17240342323527680L,17398671997927424L,17627370416504832L,17697739160682496L,17750515718815744L,17785700090904576L,17820884462993408L,17856068835082240L,17891253207171072L,18049582881570816L,19193074974457856L,23890188648316928L,24400362043604992L,25104049485381632L,25754960369025024L,26317910322446336L,26476239996846080L,26511424368934912L,26529016554979328L,26564200927068160L,26599385299156992L,26616977485201408L,26652161857290240L,26687346229379072L,26722530601467904L,27391033671155712L,29343766322085888L,29660425670885376L,29924308461551616L,30487258414972928L,30909470880038912L,31120577112571904L,31243722414882816L,31630750507859968L,32703873856569344L,33002941019324416L,33090901949546496L,33196455065812992L,33249231623946240L,33284415996035072L,33319600368123904L,33354784740212736L,33389969112301568L,33425153484390400L,33460337856479232L,33495522228568064L,34076064368033792L,34093656554078208L,34111248740122624L,34128840926167040L,34146433112211456L,34164025298255872L,34181617484300288L,34199209670344704L,34216801856389120L,34234394042433536L,34251986228477952L,34269578414522368L,34287170600566784L,34304762786611200L,34322354972655616L,34339947158700032L,34375131530788864L,34410315902877696L,34445500274966528L,34480684647055360L,34586237763321856L,35026042414432256L,35272333019054080L,35342701763231744L,35395478321364992L,35448254879498240L,35483439251587072L,35518623623675904L,35553807995764736L,35588992367853568L,35624176739942400L,35659361112031232L,35694545484120064L,35729729856208896L,35764914228297728L,35976020460830720L,36081573577097216L,36116757949186048L,38386149948915712L,50208098970763264L,51228445761339392L,52336753482137600L,52794150319292416L,53110809668091904L,58757901388349440L,59496773202214912L,59672695062659072L,62258746411188224L,62434668271632384L,63015210411098112L,65302194596872192L,65460524271271936L,66604016364158976L,68697486503444480L,68732670875533312L,68767855247622144L,68803039619710976L,68838223991799808L,68873408363888640L,68908592735977472L,68943777108066304L,68978961480155136L,69084514596421632L,69190067712688128L,69911347340509184L,70667811340419072L,70773364456685568L,70861325386907648L,71230761293840384L,71336314410106880L,71441867526373376L,72075186223972352L,72180739340238848L,72233515898372096L,74661237572501504L,76719523339698176L,76772299897831424L,76913037386186752L,144132780261900288L,144167964633989120L,144343886494433280L,144361478680477696L,144379070866522112L,144467031796744192L,144994797378076672L,146877161284829184L,148284536168382464L,153403862307307520L,153421454493351936L,153439046679396352L,153456638865440768L,153544599795662848L,153720521656107008L,153878851330506752L,164416570771111936L,288265560523800576L,288300744895889408L,288335929267978240L,288371113640067072L,288670180802822144L,288687772988866560L,288705365174910976L,288722957360955392L,288740549546999808L,288758141733044224L,288775733919088640L,288934063593488384L,289321091686465536L,289901633825931264L,290165516616597504L,297167206662275072L,306543841823948800L,306631802754170880L,306825316800659456L,306842908986703872L,306860501172748288L,306878093358792704L,306895685544837120L,306913277730881536L,306930869916925952L,307089199591325696L,307388266754080768L,307441043312214016L,307493819870347264L,307757702661013504L,327372990100537344L,331014572611731456L,576478344489467904L,576513528861556736L,576548713233645568L,576583897605734400L,576619081977823232L,576654266349912064L,576689450722000896L,576724635094089728L,576953333512667136L,577322769419599872L,577340361605644288L,577357953791688704L,577375545977733120L,577393138163777536L,577410730349821952L,577428322535866368L,577445914721910784L,577463506907955200L,577481099093999616L,577498691280044032L,577516283466088448L,577533875652132864L,577551467838177280L,577569060024221696L,577868127186976768L,578483853698531328L,578642183372931072L,579627345791418368L,579908820768129024L,580225480116928512L,580366217605283840L,595266799184904192L,613017314903719936L,613140460206030848L,613228421136252928L,613316382066475008L,613633041415274496L,613650633601318912L,613668225787363328L,613685817973407744L,613703410159452160L,613721002345496576L,613738594531540992L,613756186717585408L,613773778903629824L,613791371089674240L,613808963275718656L,613826555461763072L,613844147647807488L,613861739833851904L,613879332019896320L,614178399182651392L,614354321043095552L,614758941322117120L,614811717880250368L,614864494438383616L,614917270996516864L,614970047554650112L,615304299089494016L,615480220949938176L,616482975554469888L};

  @Override
  public List<PartitionMeta> getPartitions() {

    start = mContext.getMaxColNumInBlock();
    end = mContext.getColNum();

    int row = mContext.getRowNum();

    List<PartitionMeta> partitions = new ArrayList<>();

    int matrixId = mContext.getMatrixId();


    for (int i = 0; i < starts.length - 1; i++) {
      PartitionMeta meta = new PartitionMeta(matrixId, i, 0, row, start, starts[i+1]);
      partitions.add(meta);
      start = starts[i+1];
    }

    partitions.add(new PartitionMeta(matrixId, starts.length - 1, 0, row, start, end));

    LOG.info("partition count: " + partitions.size());
    return partitions;
  }

  @Override
  protected long getMaxIndex(MatrixContext mContext) {
    return end;
  }

  @Override
  protected long getMinIndex(MatrixContext mContext) {
    return start;
  }

}
