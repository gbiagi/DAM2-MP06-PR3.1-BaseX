xquery version "3.0";

declare option output:method "xml";
declare option output:indent "yes";

let $tags :=
  for $tagId in distinct-values(doc("anime/PostTags.xml")//row/@TagId/string())
  let $tagName := doc("anime/Tags.xml")//row[@Id = $tagId][1]/@TagName/string()
  let $usageCount := count(doc("anime/PostTags.xml")//row[@TagId = $tagId])
  where $tagName ne ""
  order by $usageCount descending
  return <tag>
            <tagName>{$tagName}</tagName>
            <usageCount>{$usageCount}</usageCount>
         </tag>

return
  <top-tags>{$tags}</top-tags>
